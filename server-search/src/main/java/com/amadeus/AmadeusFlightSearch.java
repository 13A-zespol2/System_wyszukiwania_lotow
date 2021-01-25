package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import lombok.Setter;

@Setter
/**
 *Klasa odpowiedzialna za wyszukiwanie lotów.
 */
class AmadeusFlightSearch {
    private final Amadeus amadeus;
    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String travelClass;

    public AmadeusFlightSearch(Amadeus amadeus) {
        this.amadeus = amadeus;
    }

    public FlightOfferSearch[] searchFlight() throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", originLocationCode)
                        .and("destinationLocationCode", destinationLocationCode)
                        .and("departureDate", departureDate)
                        .and("adults", "1")
                        .and("travelClass", travelClass)
                        .and("max", 20)
                        .and("nonStop", true));
    }
}
