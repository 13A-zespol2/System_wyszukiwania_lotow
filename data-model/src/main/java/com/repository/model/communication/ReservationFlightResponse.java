package com.repository.model.communication;

import java.io.Serializable;

public class ReservationFlightResponse implements Serializable {
    private final String status;
    private final boolean reverted;


    public ReservationFlightResponse(String status, boolean reverted) {
        this.status = status;
        this.reverted = reverted;
    }


}
