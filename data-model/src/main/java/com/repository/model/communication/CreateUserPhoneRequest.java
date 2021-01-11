package com.repository.model.communication;


import com.repository.model.database.UserPhone;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class CreateUserPhoneRequest implements Serializable {
    private UserPhone userPhone;
    private int phoneId;
    private int countryCallingCode;
    private int phoneNumber;
    private String deviceType;

    public CreateUserPhoneRequest() {

    }

    public CreateUserPhoneRequest(UserPhone userPhone) {
        this.userPhone = userPhone;
    }


}
