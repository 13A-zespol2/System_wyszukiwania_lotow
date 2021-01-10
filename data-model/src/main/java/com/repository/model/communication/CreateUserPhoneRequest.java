package com.repository.model.communication;

import com.repository.model.database.User;
import com.repository.model.database.UserPhone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUserPhoneRequest {
    private UserPhone userPhone;
    private int phoneId;
    private int countryCallingCode;
    private int phoneNumber;
    private String deviceType;

    public CreateUserPhoneRequest(int phoneId, int countryCallingCode, int phoneNumber, String deviceType) {
        this.phoneId = phoneId;
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
        this.deviceType = deviceType;
    }

    public CreateUserPhoneRequest(UserPhone userPhone) {
        this.userPhone = userPhone;
    }


}
