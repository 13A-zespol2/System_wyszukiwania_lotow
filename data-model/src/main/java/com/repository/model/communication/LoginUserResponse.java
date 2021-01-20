package com.repository.model.communication;

import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginUserResponse implements Serializable {
    private final String status;
    private User user;


    public LoginUserResponse(String status, User user) {
        this.status = status;
        this.user = user;

    }



    public LoginUserResponse(String status) {
        this.status = status;
    }
}
