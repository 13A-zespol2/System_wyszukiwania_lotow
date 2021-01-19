package com.repository.model.communication;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterUserRequest implements Serializable {

    private String email;
    private String password;

    public RegisterUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
