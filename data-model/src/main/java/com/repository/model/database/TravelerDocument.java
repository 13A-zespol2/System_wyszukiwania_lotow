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
    @JoinColumn(name = "myTraveler", referencedColumnName = "id")
    private MyTraveler myTraveler;

/*    public TravelerDocument(int id, String documentType, String numberDocument, String expireDate, String issuanceCountry, String nationality, MyTraveler myTraveler) {
        this.id = id;
        this.documentType = documentType;
        this.numberDocument = numberDocument;
        this.expireDate = expireDate;
        this.issuanceCountry = issuanceCountry;
        this.nationality = nationality;
        this.myTraveler = myTraveler;
    }

    public TravelerDocument(String numberDocument, String documentType) {
        this.documentType = documentType;
        this.numberDocument = numberDocument;

    }*/

    public String getDocumentType() {
        return documentType;
    }

    public String getNumberDocument() {
        return numberDocument;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getIssuanceCountry() {
        return issuanceCountry;
    }

    public String getNationality() {
        return nationality;
    }


    public static class Builder {
        private String documentType;
        private String numberDocument;
        private String expireDate;
        private String issuanceCountry;
        private String nationality;



        public Builder documentType(String documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder numberDocument(String numberDocument) {
            this.numberDocument = numberDocument;
            return this;
        }
        public Builder expireDate(String expireDate){
            this.expireDate = expireDate;
            return this;
        }
        public Builder issuanceCountry(String issuanceCountry){
            this.issuanceCountry = issuanceCountry;
            return this;
        }
        public Builder nationality(String nationality){
            this.nationality = nationality;
            return this;
        }

        public TravelerDocument build() {
            return new TravelerDocument(this);
        }
    }

    private TravelerDocument(Builder b){
        this.documentType = b.documentType;
        this.numberDocument = b.numberDocument;
        this.expireDate = b.expireDate;
        this.issuanceCountry = b.issuanceCountry;
        this.nationality = b.nationality;
    }

    public TravelerDocument() {

    }
}
