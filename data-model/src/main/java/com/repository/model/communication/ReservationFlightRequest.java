package com.repository.model.communication;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ReservationFlightRequest implements Serializable {

    private final String flightToReservation;

    public ReservationFlightRequest(String flightToReservation) {
        this.flightToReservation = flightToReservation;
    }
}
