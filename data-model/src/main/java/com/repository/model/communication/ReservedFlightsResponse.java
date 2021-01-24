package com.repository.model.communication;

import com.repository.model.data.FlightOrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ReservedFlightsResponse implements Serializable {
    private final List<FlightOrderDTO> flightOfferSearchDTOList;
    private final String status;

    public ReservedFlightsResponse(String status, List<FlightOrderDTO> flightOfferSearchDTOList) {
        this.status = status;
        this.flightOfferSearchDTOList = flightOfferSearchDTOList;
    }
}
