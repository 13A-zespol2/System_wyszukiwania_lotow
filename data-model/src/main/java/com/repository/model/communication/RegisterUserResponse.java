package com.repository.model.communication;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RegisterUserResponse implements Serializable {
    private final String status;
    private final boolean register;

    public RegisterUserResponse(String status, boolean register) {
        this.status = status;
        this.register = register;
    }
}