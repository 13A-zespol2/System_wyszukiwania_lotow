package com.repository.model.communication;

import com.repository.model.database.UserPhone;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class CreateUserPhoneResponse implements Serializable {

    private String status;
    private UserPhone userPhone;

    public CreateUserPhoneResponse(String status, UserPhone userPhone) {
        this.status = status;
        this.userPhone = userPhone;
    }

    public CreateUserPhoneResponse(String status) {
        this.status = status;
    }

    public CreateUserPhoneResponse(){

    }
}
