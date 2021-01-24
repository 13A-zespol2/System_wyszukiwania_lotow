package com.repository.model.communication;


import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientEditResponse implements Serializable {

    private String status;
    private User user;
    private MyTraveler myTraveler;
    private TravelerDocument travelerDocument;
    private TravelerPhone travelerPhone;

    public ClientEditResponse(String status) {
        this.status = status;
    }

    public ClientEditResponse(String status, User user, MyTraveler myTraveler, TravelerDocument travelerDocument, TravelerPhone travelerPhone) {
        this.status = status;
        this.user = user;
        this.myTraveler = myTraveler;
        this.travelerDocument = travelerDocument;
        this.travelerPhone = travelerPhone;
    }


}

