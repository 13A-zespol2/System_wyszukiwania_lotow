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
    private int phoneNumber;
    private String deviceType;

/*    public TravelerPhone(int id, int countryCallingCode, int phoneNumber, String deviceType) {
        this.id = id;
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
        this.deviceType = deviceType;
    }*/

/*    public TravelerPhone(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }*/

    public int getCountryCallingCode() {
        return countryCallingCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public static class Builder {
        private int countryCallingCode;
        private int phoneNumber;
        private String deviceType;

        public Builder countryCallingCode(int countryCallingCode) {
            this.countryCallingCode = countryCallingCode;
            return this;
        }

        public Builder phoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder deviceType(String deviceType){
            this.deviceType = deviceType;
            return this;
        }


        public TravelerPhone build() {
            return new TravelerPhone(this);
        }
    }

    private TravelerPhone(Builder b){
        this.countryCallingCode = b.countryCallingCode;
        this.phoneNumber = b.phoneNumber;
        this.deviceType = b.deviceType;

    }

    public TravelerPhone() {

    }
}
