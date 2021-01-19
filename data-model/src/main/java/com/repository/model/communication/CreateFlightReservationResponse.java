package com.repository.model.communication;

import java.io.Serializable;

public class CreateFlightReservationResponse implements Serializable {
    private final String status;

    public CreateFlightReservationResponse(String status) {
        this.status = status;
    }
}
