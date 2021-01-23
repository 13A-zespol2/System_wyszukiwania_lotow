package com.repository.model.communication;

import com.repository.model.data.FlightOrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservedFlightsResponse {
    private final List<FlightOrderDTO> flightOfferSearchDTOList;

    public ReservedFlightsResponse(List<FlightOrderDTO> flightOfferSearchDTOList) {
        this.flightOfferSearchDTOList = flightOfferSearchDTOList;
    }
}
