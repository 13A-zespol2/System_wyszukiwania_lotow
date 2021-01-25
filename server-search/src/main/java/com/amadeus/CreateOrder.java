package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Traveler;
import com.repository.model.database.User;

/**
 * Interfejs do tworzenia rezerwacji biletu / lotu.
 */
public interface CreateOrder {
    String createFlightOrder(Traveler[] travelers, FlightOfferSearch flightOfferSearch, boolean phone, User user) throws ResponseException;
}
