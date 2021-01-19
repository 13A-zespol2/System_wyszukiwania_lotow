package com.repository.model.communication;

import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CreateUserRequest implements Serializable {
    private String name;

    private String surname;

    private String email;

    private String phone;

    private String password;

    private Integer id;

    private String type;

    private User user;

    public CreateUserRequest() {
    }

    public CreateUserRequest(User user) {
        this.user = user;
    }
}
