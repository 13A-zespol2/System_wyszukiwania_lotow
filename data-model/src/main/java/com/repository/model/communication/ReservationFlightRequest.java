package com.repository.model.communication;

import com.repository.model.data.FlightOfferSearchDTO;
import com.repository.model.database.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ReservationFlightRequest implements Serializable {

    private final FlightOfferSearchDTO flightToReservation;
    private final User user;
    private final int quantityOfTickets;

    public ReservationFlightRequest(FlightOfferSearchDTO flightToReservation, User user, int quantityOfTickets) {
        this.flightToReservation = flightToReservation;
        this.user = user;
        this.quantityOfTickets = quantityOfTickets;
    }
}
