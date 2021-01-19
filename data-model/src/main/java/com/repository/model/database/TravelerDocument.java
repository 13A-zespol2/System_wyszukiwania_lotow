package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class TravelerDocument implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String documentType;
    private String numberDocument;
    private String expireDate;
    private String issuanceCountry;
    private String nationality;

    @OneToOne
    @JoinColumn(name = "myTraveler", referencedColumnName = "idMyTraveler")
    private MyTraveler myTraveler;

    public TravelerDocument(int id, String documentType, String numberDocument, String expireDate, String issuanceCountry, String nationality, MyTraveler myTraveler) {
        this.id = id;
        this.documentType = documentType;
        this.numberDocument = numberDocument;
        this.expireDate = expireDate;
        this.issuanceCountry = issuanceCountry;
        this.nationality = nationality;
        this.myTraveler = myTraveler;
    }
}
