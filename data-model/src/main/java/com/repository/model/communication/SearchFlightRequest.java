package com.repository.model.communication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class SearchFlightRequest implements Serializable {

    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String adults;
    private String travelClass;


    public SearchFlightRequest() {
    }

    public SearchFlightRequest(String originLocationCode, String destinationLocationCode, String departureDate, String adults, String travelClass) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
        this.departureDate = departureDate;
        this.adults = adults;
        this.travelClass = travelClass;
    }
}
