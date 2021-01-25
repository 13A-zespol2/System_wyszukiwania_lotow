package com.amadeus;

import com.ServerException;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;
import com.repository.model.database.User;


/**
 * Klasa odpowiedzialna za rezerwację biletu po stronie API.
 */
class AmadeusCreateOrder implements CreateOrder {
    private final Amadeus amadeus;

    public AmadeusCreateOrder(Amadeus amadeus) {
        this.amadeus = amadeus;
    }


    /**
     * Sprawdza czy jest dostępna podana liczba biletów dla danego lotu.
     *
     * @param flightOfferSearch
     * @return
     */
    private FlightPrice checkAvailability(FlightOfferSearch flightOfferSearch) {
        try {
            return amadeus.shopping.flightOffersSearch.pricing.post(flightOfferSearch);
        } catch (ResponseException e) {
            throw new ServerException("Api error check availability flight: " + e);
        }
    }


    /**
     * Metoda odpowiedzialna za tworzenie rezerwacji.
     *
     * @param travelers
     * @param flightOfferSearch
     * @param phone
     * @param user
     * @return
     * @throws ResponseException
     */
    @Override
    public String createFlightOrder(Traveler[] travelers, FlightOfferSearch flightOfferSearch, boolean phone, User user) throws ResponseException {
        FlightPrice flightPrice = checkAvailability(flightOfferSearch);
        if (flightPrice == null)
            return null;

        return amadeus.booking.flightOrders.post(flightPrice, travelers).getId();

    }
}
