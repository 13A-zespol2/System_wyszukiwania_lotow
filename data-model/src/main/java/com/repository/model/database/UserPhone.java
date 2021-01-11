package com.repository.model.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserPhone implements Serializable {
    @Id
    @GeneratedValue
    private int phoneId;
    private int countryCallingCode;
    private int phoneNumber;
    private String deviceType;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    public UserPhone( int countryCallingCode, int phoneNumber, String deviceType){

        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
        this.deviceType = deviceType;
    }

    public UserPhone(){

    }
}
