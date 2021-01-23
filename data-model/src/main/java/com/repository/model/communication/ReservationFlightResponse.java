package com.repository.model.communication;

public class ReservationFlightResponse {
    private final String status;
    private final boolean reverted;


    public ReservationFlightResponse(String status, boolean reverted) {
        this.status = status;
        this.reverted = reverted;
    }


}
