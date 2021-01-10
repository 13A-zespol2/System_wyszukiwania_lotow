package com.anonymus;

import com.ServerException;
import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.repository.model.communication.CreateFlightReservationRequest;
import com.repository.model.communication.SearchFlightRequest;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AmadeusFacade {


    private final AmadeusFlightSearch amadeusFlightSearch;
    private final AmadeusOrderManagement amadeusOrderManagement;
    private final AmadeusCreateOrder amadeusCreateOrder;

    public AmadeusFacade() {
        Amadeus amadeus = Amadeus
                .builder("8LEPfmoU2BlW8PtxGwE2iu8HrjwAERYQ", "M5oxj0KGEobsAgGQ")
                .build();

        this.amadeusFlightSearch = new AmadeusFlightSearch(amadeus);
        this.amadeusOrderManagement = new AmadeusOrderManagement(amadeus);
        this.amadeusCreateOrder = new AmadeusCreateOrder(amadeus);
    }


    public Optional<Object> createOrderFlight(CreateFlightReservationRequest createFlightReservation) {
        //TODO
        return null;


    }

    public Optional<Object> getOrderedFlight(String idOrder) {
        return Optional.empty();
    }


    public Optional<List<FlightOfferSearch>> searchFlight(SearchFlightRequest searchFlightRequest) {
        FlightOfferSearch[] flightOfferSearches = null;
        amadeusFlightSearch.setOriginLocationCode(searchFlightRequest.getOriginLocationCode());
        amadeusFlightSearch.setDestinationLocationCode(searchFlightRequest.getDestinationLocationCode());
        amadeusFlightSearch.setDepartureDate(searchFlightRequest.getDepartureDate());
        amadeusFlightSearch.setReturnDate(searchFlightRequest.getReturnDate());
        amadeusFlightSearch.setAdults(String.valueOf(searchFlightRequest.getAdults()));
        amadeusFlightSearch.setTravelClass(searchFlightRequest.getTravelClass());
        amadeusFlightSearch.setChildren(String.valueOf(searchFlightRequest.getChildren()));
        amadeusFlightSearch.setInfants(String.valueOf(searchFlightRequest.getInfants()));
        amadeusFlightSearch.setNonStop(String.valueOf(searchFlightRequest.isNonStop()));
        try {
            flightOfferSearches = amadeusFlightSearch.searchFlight();

        } catch (ResponseException e) {
            throw new ServerException("Api error search flight: " + e);
        }
        return flightOfferSearches != null ? Optional.of(List.of(flightOfferSearches)) : Optional.empty();
    }
}
