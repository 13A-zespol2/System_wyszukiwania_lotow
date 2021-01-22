package com.repository.model.adapter;

import com.amadeus.resources.FlightOfferSearch;
import com.repository.model.data.Flight;

import java.util.Arrays;

public class FlightOfferSearchAdapter extends Flight {
    private final FlightOfferSearch flightOfferSearch;

    public FlightOfferSearchAdapter(FlightOfferSearch flightOfferSearch) {
        this.flightOfferSearch = flightOfferSearch;
    }

    public void initialize() {
        id = flightOfferSearch.getId();
        departureIATA = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode();
        destinationIATA = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getIataCode();
        departureTime = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt();
        arrivalTime = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getAt();
        ticketPrice = flightOfferSearch.getPrice().getTotal();
        currency = flightOfferSearch.getPrice().getCurrency();
        flightClass = flightOfferSearch.getPrice().getClass().getName();
    }

}
