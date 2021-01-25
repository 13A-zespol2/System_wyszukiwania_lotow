package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
@Getter
@Setter
/**
 * Klasa odpowiadająca za tabelę ,,Traveler Phone". Zastosowany jest w niej wzorzec projektowy ,,Builder".
 * Deklaracja ,,@Entity" konwertuje tą klasę na klasę bazodanową i tworzy połączenie z innymi tabelami (klasami).
 */
public class TravelerPhone implements Serializable {
    @Id
    @SequenceGenerator(name = "travelerPhoneGenerator", sequenceName = "myPhTrSeq", initialValue = 1)
    @GeneratedValue(generator = "travelerPhoneGenerator")
    private int id;
    private int countryCallingCode;
    private int phoneNumber;
    private String deviceType;



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
