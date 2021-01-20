package com.repository.model.communication;

import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RegisterUserResponse implements Serializable {
    private final String status;
    private User user;

    public RegisterUserResponse(String status) {
        this.status = status;
    }
}