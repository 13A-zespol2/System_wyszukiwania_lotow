package com.repository.model.data;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Getter
public class FlightOrderDTO implements Serializable {
    private final int id;
    private final String departureIATA;
    private final String destinationIATA;
    private final String departureTime;
    private final String arrivalTime;
    private final double ticketPrice;
    private final String currency;
    private final String flightClass;
    private final int quantityOfTickets;

    public FlightOrderDTO(FlightOrder flightOrder, int quantityOfTickets, int id) {

        this.id = id;
        departureIATA = Objects.requireNonNull(Objects.requireNonNull(Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> Arrays.stream(e.getItineraries())
                        .map(q -> Arrays.stream(q.getSegments())
                                .map(s -> s.getDeparture().getIataCode())))
                .findFirst().orElse(null)).findFirst().orElse(null)).findFirst().orElse("");

        destinationIATA = Objects.requireNonNull(Objects.requireNonNull(Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> Arrays.stream(e.getItineraries())
                        .map(q -> Arrays.stream(q.getSegments())
                                .map(s -> s.getArrival().getIataCode())))
                .findFirst().orElse(null)).findFirst().orElse(null)).findFirst().orElse("");

        departureTime = Objects.requireNonNull(Objects.requireNonNull(Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> Arrays.stream(e.getItineraries())
                        .map(q -> Arrays.stream(q.getSegments())
                                .map(s -> s.getDeparture().getAt())))
                .findFirst().orElse(null)).findFirst().orElse(null)).findFirst().orElse("");


        arrivalTime = Objects.requireNonNull(Objects.requireNonNull(Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> Arrays.stream(e.getItineraries())
                        .map(q -> Arrays.stream(q.getSegments())
                                .map(s -> s.getArrival().getAt())))
                .findFirst().orElse(null)).findFirst().orElse(null)).findFirst().orElse("");

        ticketPrice = Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> e.getPrice().getTotal())
                .findFirst().orElse(0.0);

        currency = Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> e.getPrice().getCurrency())
                .findFirst().orElse(null);

        flightClass = Objects.requireNonNull(Arrays.stream(flightOrder.getFlightOffers())
                .map(e -> Arrays.stream(e.getTravelerPricings())
                        .map(d -> Arrays.stream(d.getFareDetailsBySegment())
                                .map(FlightOfferSearch.FareDetailsBySegment::getCabin))
                        .findFirst()
                        .orElse(null))
                .findFirst().orElse(null))
                .findFirst().orElse(null);


        this.quantityOfTickets = quantityOfTickets;
    }

}
