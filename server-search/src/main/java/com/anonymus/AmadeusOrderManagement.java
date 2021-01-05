package com.anonymus;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOrder;

class AmadeusOrderManagement {
    private final Amadeus amadeus;

    public AmadeusOrderManagement(Amadeus amadeus) {
        this.amadeus = amadeus;
    }


    public FlightOrder getOrder(String idOrder) throws ResponseException {
        return amadeus.booking.flightOrder(idOrder).get();
    }

}
