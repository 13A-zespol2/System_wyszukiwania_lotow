package com.repository.model.communication;

import com.amadeus.resources.FlightOfferSearch;
import com.repository.model.database.MyTraveler;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter

public class CreateFlightReservationRequest implements Serializable {
    private List<MyTraveler> travelers;
    private FlightOfferSearch flightOfferSearch;

    public CreateFlightReservationRequest(List<MyTraveler> travelers, FlightOfferSearch flightOfferSearch) {
        this.travelers = travelers;
        this.flightOfferSearch = flightOfferSearch;
    }


}
