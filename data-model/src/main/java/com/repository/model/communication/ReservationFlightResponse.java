package com.repository.model.communication;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter

public class ReservationFlightResponse implements Serializable {
    private final String status;
    private final boolean reverted;


    public ReservationFlightResponse(String status, boolean reverted) {
        this.status = status;
        this.reverted = reverted;
    }


}
