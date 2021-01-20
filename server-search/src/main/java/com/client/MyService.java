package com.client;


import com.amadeus.AmadeusFacade;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.Gson;
import com.repository.ReservationRepository;
import com.repository.UserRepository;
import com.repository.model.communication.*;
import com.repository.model.database.User;
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
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service

public class MyService implements Serializable {
    private final AmadeusFacade amadeusFacade = new AmadeusFacade();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public void start(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("START SERVER");
        //TODO obsluga enuma
  /*      for (Map.Entry<String, AirportCode> entry : AirportCode.getByIata().entrySet())
        {
            log.info(entry.getKey());
            log.info(entry.getValue().name());
        }
        AirportCode warsaw = AirportCode.valueOf("WARSAW");
     */
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

            close(clientSocket, out, in);
        }
    }

    private SearchFlightResponse findSearch(SearchFlightRequest request) {
        Optional<List<FlightOfferSearch>> flightOfferSearches = Optional.ofNullable(amadeusFacade.searchFlight(request));
        if (flightOfferSearches.isEmpty())
            return new SearchFlightResponse("Nie znaleziono lotu");

        List<FlightOfferSearch> flightOfferSearches1 = flightOfferSearches.get();



        List<String> collect = flightOfferSearches1.stream().map(e -> new Gson().toJson(e)).collect(Collectors.toList());
        return new SearchFlightResponse("Zanaleziono",collect );
    }


    private LoginUserResponse findUserToLogin(LoginUserRequest loginUserRequest) {
        User user = userRepository.findUserByEmailAndPassword(loginUserRequest.getEmail(), loginUserRequest.getPassword());
        if (user != null) {
            log.info("znaleziono");
            return new LoginUserResponse(user, "ZALOGOWANO");
        }

        return new LoginUserResponse("BLEDNY LOGIN LUB HASLO");

    }

    private RegisterUserResponse userToRegister(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());
        user = userRepository.save(user);


        return user == null ? new RegisterUserResponse("NIE ZAREJESTROWANO") : new RegisterUserResponse("ZAREJESTROWANO");
    }


    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }
}
