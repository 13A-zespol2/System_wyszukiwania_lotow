package com.notifer;

import com.amadeus.resources.FlightOfferSearch;

import javax.mail.MessagingException;

public interface Notifier {
    void sendNotification(String contact, FlightOfferSearch flightOrder) throws MessagingException;

}
