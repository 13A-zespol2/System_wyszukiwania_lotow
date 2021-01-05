package com.repository.model.communication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFlightRequest {

    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private int adults;
    private String travelClass;
    private int infants;
    private int children;
    private boolean returnFlight;
    private String returnDate;
    private boolean nonStop;

    public SearchFlightRequest() {
    }

    public SearchFlightRequest(String originLocationCode, String destinationLocationCode, String departureDate, int adults, String travelClass, int infants, int children, boolean returnFlight, String returnDate) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
        this.departureDate = departureDate;
        this.adults = adults;
        this.travelClass = travelClass;
        this.infants = infants;
        this.children = children;
        this.returnFlight = returnFlight;
        this.returnDate = returnDate;
    }
}
