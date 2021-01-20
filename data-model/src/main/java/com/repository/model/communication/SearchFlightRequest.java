package com.repository.model.communication;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchFlightRequest implements Serializable {

    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String adults;
    private String travelClass;
    private String children;
    private boolean returnFlight;
    private String returnDate;


    public SearchFlightRequest() {
    }

    public SearchFlightRequest(String originLocationCode, String destinationLocationCode, String departureDate, String adults, String travelClass, String children, boolean returnFlight, String returnDate) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
        this.departureDate = departureDate;
        this.adults = adults;
        this.travelClass = travelClass;
        this.children = children;
        this.returnFlight = returnFlight;
        this.returnDate = returnDate;
    }
}
