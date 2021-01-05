package com.anonymus;

import com.ServerException;
import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;

class AmadeusCreateOrder {
    private final Amadeus amadeus;

    public AmadeusCreateOrder(Amadeus amadeus) {
        this.amadeus = amadeus;
    }

    private FlightPrice checkAvailability(FlightOfferSearch flightOfferSearch) {
        try {
            return amadeus.shopping.flightOffersSearch.pricing.post(flightOfferSearch);
        } catch (ResponseException e) {
            throw new ServerException("Api error check availability flight: " + e);
        }
    }

    public String createFlightOrder(Traveler[] travelers, FlightOfferSearch flightOfferSearch) throws ResponseException {
        FlightPrice flightPrice = checkAvailability(flightOfferSearch);
        if (flightPrice == null)
            return null;

        return amadeus.booking.flightOrders.post(flightPrice, travelers).getId();

    }
}
