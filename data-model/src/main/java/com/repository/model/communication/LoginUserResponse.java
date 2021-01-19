package com.repository.model.communication;

import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginUserResponse implements Serializable {
    private final String status;
    private User user;

    public LoginUserResponse(User user, String status) {
        this.status = status;
        this.user = user;
    }

    public LoginUserResponse(String status) {
        this.status = status;
    }
}
