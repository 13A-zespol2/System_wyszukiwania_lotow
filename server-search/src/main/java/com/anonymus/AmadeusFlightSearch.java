package com.anonymus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import lombok.Setter;

@Setter
class AmadeusFlightSearch {
    private final Amadeus amadeus;
    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private String nonStop;
    private String adults;
    private String travelClass;
    private String children;
    private String infants;

    public AmadeusFlightSearch(Amadeus amadeus) {
        this.amadeus = amadeus;
    }

    public FlightOfferSearch[] searchFlight() throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", originLocationCode)
                        .and("destinationLocationCode", destinationLocationCode)
                        .and("departureDate", departureDate)
                        .and("returnDate", returnDate)
                        .and("nonStop", nonStop)
                        .and("adults", adults)
                        .and("travelClass", travelClass)
                        .and("children", children)
                        .and("infants", infants)
                        .and("max", 20));
    }


}
