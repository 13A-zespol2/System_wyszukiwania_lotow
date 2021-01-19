package com.repository.model.communication;


import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateUserResponse implements Serializable {

    private String status;
    private User user;

    public CreateUserResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public CreateUserResponse(String status) {
        this.status = status;
    }


    public CreateUserResponse() {
    }


}
