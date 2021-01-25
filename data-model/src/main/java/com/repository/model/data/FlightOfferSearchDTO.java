package com.repository.model.data;

import com.amadeus.resources.FlightOfferSearch;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

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

        departureIATA = Objects.requireNonNull(Arrays.stream(flightOfferSearch.getItineraries())
                .map(e -> Arrays.stream(e.getSegments())
                        .map(q -> q.getDeparture().getIataCode()))
                .findFirst().orElse(null)).findFirst().orElse("");

        destinationIATA = Objects.requireNonNull(Arrays.stream(flightOfferSearch.getItineraries())
                .map(e -> Arrays.stream(e.getSegments())
                        .map(q -> q.getArrival().getIataCode()))
                .findFirst().orElse(null)).findFirst().orElse("");


        departureTime = Objects.requireNonNull(Arrays.stream(flightOfferSearch.getItineraries())
                .map(e -> Arrays.stream(e.getSegments())
                        .map(q -> q.getDeparture().getAt())).findFirst()
                .orElse(null)).findFirst().orElse("");

        arrivalTime = Objects.requireNonNull(Arrays.stream(flightOfferSearch.getItineraries())
                .map(e -> Arrays.stream(e.getSegments())
                        .map(q -> q.getArrival().getAt())).findFirst()
                .orElse(null)).findFirst().orElse("");

        ticketPrice = flightOfferSearch.getPrice().getTotal();

        currency = flightOfferSearch.getPrice().getCurrency();

        flightClass = Objects.requireNonNull(Arrays.stream(flightOfferSearch.getTravelerPricings())
                .map(e -> Arrays.stream(e.getFareDetailsBySegment())
                        .map(FlightOfferSearch.FareDetailsBySegment::getCabin)).findFirst().orElse(null)).findFirst().orElse("Sda");


    }


}
