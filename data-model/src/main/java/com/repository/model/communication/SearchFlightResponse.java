package com.repository.model.communication;

import com.amadeus.resources.FlightOfferSearch;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SearchFlightResponse implements Serializable {

    private String status;
    private List<FlightOfferSearch> tList;
}
