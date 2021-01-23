package com.repository.model.data;

import com.amadeus.resources.FlightOfferSearch;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Setter

public class FlightOfferSearchDTO implements Serializable {
    private String id;
    private String departureIATA;
    private String destinationIATA;
    private String departureTime;
    private String arrivalTime;
    private double ticketPrice;
    private String currency;
    private String flightClass;


    public FlightOfferSearchDTO(FlightOfferSearch flightOfferSearch) {
        id = flightOfferSearch.getId();
        departureIATA = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode();
        destinationIATA = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getIataCode();
        departureTime = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt();
        arrivalTime = Arrays.stream(Arrays.stream(flightOfferSearch.getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getAt();
        ticketPrice = flightOfferSearch.getPrice().getTotal();
        currency = flightOfferSearch.getPrice().getCurrency();
        flightClass = Arrays.stream(Arrays.stream(flightOfferSearch.getTravelerPricings()).findFirst().get().getFareDetailsBySegment()).findFirst().get().getCabin();
    }



}
