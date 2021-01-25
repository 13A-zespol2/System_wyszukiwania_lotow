package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOrder;


/**
 * Klasa odpowiedzialna za pobieranie zarezerwowanych lotów w API.
 */
class AmadeusOrderManagement {
    private final Amadeus amadeus;

    public AmadeusOrderManagement(Amadeus amadeus) {
        this.amadeus = amadeus;
    }


    /**
     * Pobiera dane z API Amadeus.
     *
     * @param idOrder Identyfikator rezerwacji.
     * @return
     * @throws ResponseException
     */
    public FlightOrder getOrder(String idOrder) throws ResponseException {
        return amadeus.booking.flightOrder(idOrder).get();
    }

}
