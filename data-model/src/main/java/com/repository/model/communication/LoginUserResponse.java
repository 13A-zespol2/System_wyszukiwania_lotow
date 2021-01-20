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
    private MyTraveler myTraveler;
    private TravelerPhone travelerPhone;
    private TravelerDocument travelerDocument;

    public LoginUserResponse(String status, User user) {
        this.status = status;
        this.user = user;

    }

    public LoginUserResponse(String status, User user, MyTraveler myTraveler, TravelerPhone travelerPhone, TravelerDocument travelerDocument) {
        this.status = status;
        this.user = user;
        this.myTraveler = myTraveler;
        this.travelerPhone = travelerPhone;
        this.travelerDocument = travelerDocument;
    }


    public LoginUserResponse(String status) {
        this.status = status;
    }
}
