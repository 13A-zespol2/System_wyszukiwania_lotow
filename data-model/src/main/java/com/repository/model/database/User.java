package com.repository.model.database;

import com.repository.model.builder.user.BuilderUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User implements Serializable, BuilderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String surname;

    private String email;

    private String dateOfBirth;


    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User() {
    }


    @Override
    public void setIdTraveler(int idTraveler) {
        this.id = idTraveler;
    }


    @Override
    public void setNumber(String phoneNumber) {

    }

    @Override
    public void setCountryCallCode(String countryCallCode) {

    }

    @Override
    public void setDocumentType(String documentType) {

    }

    @Override
    public void setDocumentNumber(String documentNumber) {

    }

    @Override
    public void setIssuanceCountry(String issuanceCountry) {

    }

    @Override
    public void setNationality(String nationality) {

    }

    @Override
    public void setHolder(boolean holder) {

    }
}