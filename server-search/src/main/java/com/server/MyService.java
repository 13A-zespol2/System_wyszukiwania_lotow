package com.server;


import com.ServerException;
import com.amadeus.AmadeusFacade;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.Traveler;
import com.repository.*;
import com.repository.model.communication.*;
import com.repository.model.data.FlightOfferSearchDTO;
import com.repository.model.data.FlightOrderDTO;
import com.repository.model.database.*;
import com.strategy.UserBasedTravelerCreationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service

public class MyService implements Serializable {
    private final AmadeusFacade amadeusFacade = new AmadeusFacade();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyTravelerRepository myTravelerRepository;
    @Autowired
    private TravelerDocumentRepository travelerDocumentRepository;
    @Autowired
    private TravelerPhoneRepository travelerPhoneRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public void start(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("START SERVER");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            Object request = in.readObject();

            if (request instanceof LoginUserRequest) {
                LoginUserResponse loginUserResponse = findUserToLogin((LoginUserRequest) request);
                out.writeObject(loginUserResponse);
            }


            if (request instanceof SearchFlightRequest) {
                SearchFlightResponse searchFlightResponse = findSearch((SearchFlightRequest) request);
                out.writeObject(searchFlightResponse);
            }

            if (request instanceof RegisterUserRequest) {
                RegisterUserResponse registerUserResponse = userToRegister((RegisterUserRequest) request);
                out.writeObject(registerUserResponse);
            }

            if (request instanceof ClientDataRequest) {
                ClientDataResponse clientDataResponse = dataToShow((ClientDataRequest) request);
                out.writeObject(clientDataResponse);
            }

            if (request instanceof ReservationFlightRequest) {
                ReservationFlightResponse reservationFlightResponse = flightReservation((ReservationFlightRequest) request);
                out.writeObject(reservationFlightResponse);
            }

            if (request instanceof ReservedFlightsRequest) {
                ReservedFlightsResponse reservationFlightResponse = findReservedFlight((ReservedFlightsRequest) request);
                out.writeObject(reservationFlightResponse);
            }

            if (request instanceof ClientEditRequest) {
                ClientEditResponse clientEditResponse = editData((ClientEditRequest) request);
                out.writeObject(clientEditResponse);
            }


            close(clientSocket, out, in);
        }
    }

    private ReservedFlightsResponse findReservedFlight(ReservedFlightsRequest request) {
        User user = request.getUser();
        MyTraveler byUserId = myTravelerRepository.findByUserId(user.getId());
        if (byUserId == null)
            return new ReservedFlightsResponse("TRAVELER NOT FOUND", null);
        List<Reservation> byMyTravelerId = reservationRepository.findByMyTravelerId(byUserId.getId());

        List<FlightOrderDTO> collect1 = new ArrayList<>();
        FlightOrderDTO flightOrderDTO = null;
        int i = 1;
        for (Reservation elemRes : byMyTravelerId) {
            FlightOrder flightOrder = amadeusFacade.getOrderedFlight(elemRes.getReservationApiCode()).orElse(null);
            if (flightOrder != null)
                flightOrderDTO = new FlightOrderDTO(flightOrder, elemRes.getQuantityOfTickets(), i);
            if (flightOrderDTO != null) {
                collect1.add(flightOrderDTO);
                i++;
            }

        }


        return new ReservedFlightsResponse("FOUND", collect1);
    }

    private ReservationFlightResponse flightReservation(ReservationFlightRequest request) {


        FlightOfferSearchDTO flightToReservation = request.getFlightToReservation();

        User user = request.getUser();
        MyTraveler myTraveler = myTravelerRepository.findByUserId(user.getId());
        if (myTraveler == null)
            return new ReservationFlightResponse("FILL ALL TRAVELER DATA", false);


        String parse = flightToReservation.getArrivalTime().substring(0, 10);


        List<FlightOfferSearch> flightOfferSearches = amadeusFacade.searchFlight(flightToReservation.getDestinationIATA(),
                flightToReservation.getDepartureIATA(), parse, flightToReservation.getFlightClass()).orElse(null);
        TravelerPhone byId = travelerPhoneRepository.findById(myTraveler.getTravelerPhone().getId());
        TravelerDocument byMyTravelerId = travelerDocumentRepository.findByMyTravelerId(myTraveler.getId());
        Traveler travlerFromBase = new UserBasedTravelerCreationStrategy(myTraveler, byMyTravelerId, byId).createTraveler();


        FlightOfferSearch flightOfferSearch = null;
        for (FlightOfferSearch elem : flightOfferSearches) {
            if (elem.getId().equals(flightToReservation.getId())) {
                flightOfferSearch = elem;
                break;
            }
        }
        String orderFlight = null;
        Traveler[] travelers = {travlerFromBase};

        if (flightOfferSearch != null)
            try {
                orderFlight = amadeusFacade.createOrderFlight(travelers, flightOfferSearch, user, request.isPhoneNotifaction()).orElse(null);

            } catch (ServerException e) {
                new ReservationFlightResponse("CANT BOOK FLIGHT", false);

            }

        Reservation save = reservationRepository.save(new Reservation(orderFlight, myTraveler, request.getQuantityOfTickets()));
        if (save != null)
            return new ReservationFlightResponse("BOOKED FLIGHT", true);


        return new ReservationFlightResponse("CANT BOOK FLIGHT", false);
    }

    private SearchFlightResponse findSearch(SearchFlightRequest request) {
        List<FlightOfferSearch> flightOfferSearches = amadeusFacade.searchFlight(
                request.getOriginLocationCode(), request.getDestinationLocationCode(), request.getDepartureDate(), request.getTravelClass()
        ).orElse(null);

        if (flightOfferSearches == null)
            return new SearchFlightResponse("FLIGHT NOT FOUND");

        List<FlightOfferSearchDTO> collect = flightOfferSearches.stream()
                .map(FlightOfferSearchDTO::new)
                .collect(Collectors.toList());


        return new SearchFlightResponse("FOUND", collect);
    }


    private LoginUserResponse findUserToLogin(LoginUserRequest loginUserRequest) {
        User user = userRepository.findUserByEmailAndPassword(loginUserRequest.getEmail(), loginUserRequest.getPassword());


        if (user != null) {
            log.info("znaleziono");
            //System.out.println(myTraveler.getDateOfBirth());
            return new LoginUserResponse("LOGIN", user);

        }

        return new LoginUserResponse("WRONG LOGIN OR PASSWORD");

    }


    private RegisterUserResponse userToRegister(RegisterUserRequest registerUserRequest) {
        User user = new User.Builder().email(registerUserRequest.getEmail()).password(registerUserRequest.getPassword()).build();

        try {
             userRepository.save(user);
        } catch (Exception e) {
            return new RegisterUserResponse("THIS EMAIL ALREADY IN USE", false);
        }

        return new RegisterUserResponse("USER REGISTERED", true);
    }


    private ClientEditResponse editData(ClientEditRequest clientEditRequest) {

        User user = clientEditRequest.getUser();
        MyTraveler myTravelerRequest = clientEditRequest.getMyTraveler();
        TravelerDocument travelerDocumentRequest = clientEditRequest.getTravelerDocument();
        TravelerPhone travelerPhoneRequest = clientEditRequest.getTravelerPhone();

        TravelerDocument travelerDocument = null;
        TravelerPhone travelerPhone = null;


        MyTraveler myTraveler = myTravelerRepository.findByUserId(user.getId());
        if (myTraveler != null) {
            travelerDocument = travelerDocumentRepository.findByMyTravelerId(myTraveler.getId());
            travelerPhone = travelerPhoneRepository.findById(myTraveler.getTravelerPhone().getId());
        }

        if (myTraveler == null || travelerDocument == null || travelerPhone == null) {
            myTraveler = new MyTraveler();
            travelerDocument = new TravelerDocument();
            travelerPhone = new TravelerPhone();
        }

        myTraveler.setName(myTravelerRequest.getName());
        myTraveler.setSurname(myTravelerRequest.getSurname());
        myTraveler.setDateOfBirth(myTravelerRequest.getDateOfBirth());

        travelerDocument.setDocumentType(travelerDocumentRequest.getDocumentType());
        travelerDocument.setNumberDocument(travelerDocumentRequest.getNumberDocument());
        travelerDocument.setExpireDate(travelerDocumentRequest.getExpireDate());
        travelerDocument.setIssuanceCountry("PL");
        travelerDocument.setNationality("PL");


        travelerPhone.setDeviceType("MOBILE");
        travelerPhone.setCountryCallingCode(48);

        travelerPhone.setPhoneNumber(travelerPhoneRequest.getPhoneNumber());

        user.setPassword(user.getPassword());

        myTraveler.setUser(user);


        userRepository.save(user);
        TravelerPhone travelerPhoneSave = travelerPhoneRepository.save(travelerPhone);
        myTraveler.setTravelerPhone(travelerPhoneSave);
        MyTraveler myTravelerSave = myTravelerRepository.save(myTraveler);
        travelerDocument.setMyTraveler(myTravelerSave);
        travelerDocumentRepository.save(travelerDocument);

        return new ClientEditResponse("DATA CHANGED");
    }


    private ClientDataResponse dataToShow(ClientDataRequest clientDataRequest) {


        User user = clientDataRequest.getUser();

        MyTraveler myTraveler = myTravelerRepository.findByUserId(user.getId());


        if (myTraveler == null) {
            return new ClientDataResponse("TRAVELER NULL");
        }
        TravelerDocument travelerDocument = travelerDocumentRepository.findByMyTravelerId(myTraveler.getId());

        if (travelerDocument == null) {
            return new ClientDataResponse("DOCUMENT NULL");
        }

        TravelerPhone travelerPhone = travelerPhoneRepository.findById(myTraveler.getTravelerPhone().getId());
        if (travelerPhone == null) {
            return new ClientDataResponse("PHONE NULL");
        }
        return new ClientDataResponse("USER DATA", user, myTraveler, travelerDocument, travelerPhone);
    }


    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }
}
