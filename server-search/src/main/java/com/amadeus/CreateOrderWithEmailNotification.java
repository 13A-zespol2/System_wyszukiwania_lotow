package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Traveler;
import com.notifer.EmailNotifier;
import com.notifer.PhoneNotifier;
import com.repository.model.database.User;
import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import java.util.Arrays;

@Slf4j
public class CreateOrderWithEmailNotification implements CreateOrder {

    private final EmailNotifier emailNotifier = new EmailNotifier();
    private final PhoneNotifier phoneNotifier = new PhoneNotifier();
    private final CreateOrder createOrder;

    public CreateOrderWithEmailNotification(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @Override
    public String createFlightOrder(Traveler[] travelers, FlightOfferSearch flightOfferSearch, boolean phone, User user) throws ResponseException {

        String flightOrder = createOrder.createFlightOrder(travelers, flightOfferSearch, phone, user);
        if (flightOrder != null) {
            String message = "ZAREZERWOWANO BILET Z: " + Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode()
                    + " \nDO: " + Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getIataCode()
                    + " \nKOSZT BILETU TO: " + flightOfferSearch.getPrice().getTotal() + " " + flightOfferSearch.getPrice().getCurrency()
                    + " \nDATA WYLOTU: " + Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt().replace("T", "  ");
            String emailAddress = user.getEmail();
            try {
                emailNotifier.sendNotification(emailAddress, message);
            } catch (MessagingException e) {
                log.info("Cannot send email: " + e);
            }
            if (phone) {
                String phoneNumber = Arrays.stream(travelers[0].getContact().getPhones()).findFirst().get().getNumber();
                phoneNotifier.sendNotification("", message);
            }

        }

        return flightOrder;

    }
}
