package com.amadeus;

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
    private String adults;
    private String travelClass;
    private String children;

    public AmadeusFlightSearch(Amadeus amadeus) {
        this.amadeus = amadeus;
    }

    public FlightOfferSearch[] searchFlight() throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", originLocationCode)
                        .and("destinationLocationCode", destinationLocationCode)
                        .and("departureDate", departureDate)
                        .and("returnDate", returnDate)
                        .and("adults", adults)
                        .and("travelClass", travelClass)
                        .and("children", children)
                        .and("max", 20));
    }


}
