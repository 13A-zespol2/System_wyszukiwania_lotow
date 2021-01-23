package com.repository.model.data;

import com.amadeus.resources.FlightOrder;

import java.io.Serializable;
import java.util.Arrays;


public class FlightOrderDTO implements Serializable {
    private final String id;
    private final String departureIATA;
    private final String destinationIATA;
    private final String departureTime;
    private final String arrivalTime;
    private final double ticketPrice;
    private final String currency;
    private final String flightClass;
    private final int quantityOfTickets;

    public FlightOrderDTO(FlightOrder flightOrder, int quantityOfTickets) {

        id = flightOrder.getId();
        departureIATA = Arrays.stream(Arrays.stream(Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode();
        destinationIATA = Arrays.stream(Arrays.stream(Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode();
        departureTime = Arrays.stream(Arrays.stream(Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt();
        arrivalTime = Arrays.stream(Arrays.stream(Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getAt();
        ticketPrice = Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getPrice().getTotal();
        currency = Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getPrice().getCurrency();
        flightClass = Arrays.stream(Arrays.stream(Arrays.stream(flightOrder.getFlightOffers()).findFirst().get().getTravelerPricings()).findFirst().get().getFareDetailsBySegment()).findFirst().get().getCabin();
        this.quantityOfTickets = quantityOfTickets;
    }

}
