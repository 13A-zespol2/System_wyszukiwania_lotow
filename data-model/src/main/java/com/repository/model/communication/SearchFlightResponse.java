package com.repository.model.communication;

import com.repository.model.data.FlightOfferSearchDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SearchFlightResponse implements Serializable {

    private String status;
    private List<FlightOfferSearchDTO> tList;


    public SearchFlightResponse(String status, List<FlightOfferSearchDTO> tList) {
        this.status = status;
        this.tList = tList;
    }

    public SearchFlightResponse(String status) {
        this.status = status;
    }
}
