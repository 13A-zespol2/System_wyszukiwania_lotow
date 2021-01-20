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
public class ClientDataRequest implements Serializable {
    private User user;


    public ClientDataRequest(User user) {
        this.user = user;

    }
}
