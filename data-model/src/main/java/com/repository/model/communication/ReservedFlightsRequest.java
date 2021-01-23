package com.repository.model.communication;

import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ReservedFlightsRequest implements Serializable {
    private final User user;

    public ReservedFlightsRequest(User user) {
        this.user = user;
    }
}
