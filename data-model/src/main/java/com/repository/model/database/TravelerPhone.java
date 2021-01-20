package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class TravelerPhone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int countryCallingCode;
    private String phoneNumber;
    private String deviceType;

    public TravelerPhone(int id, int countryCallingCode, String phoneNumber, String deviceType) {
        this.id = id;
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
        this.deviceType = deviceType;
    }


    public TravelerPhone() {
    }
}
