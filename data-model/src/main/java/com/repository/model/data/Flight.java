package com.repository.model.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flight {
    protected String id;
    protected String departureIATA;
    protected String destinationIATA;
    protected String departureTime;
    protected String arrivalTime;
    protected double ticketPrice;
    protected String currency;
    protected String flightClass;

}
