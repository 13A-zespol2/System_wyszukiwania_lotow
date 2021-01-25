package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
/**
 * Klasa odpowiadająca za tabelę ,,Traveler Document". Zastosowany jest w niej wzorzec projektowy ,,Builder".
 * Deklaracja ,,@Entity" konwertuje tą klasę na klasę bazodanową i tworzy połączenie z innymi tabelami (klasami).
 */
public class TravelerDocument implements Serializable {
    @Id
    @SequenceGenerator(name = "travelerDocGenerator", sequenceName = "myDoTrSeq", initialValue = 1)
    @GeneratedValue(generator = "travelerPhoneGenerator")
    private int id;
    private String documentType;
    private String numberDocument;
    private String expireDate;
    private String issuanceCountry;
    private String nationality;

    @OneToOne
    @JoinColumn(name = "myTraveler", referencedColumnName = "id")
    private MyTraveler myTraveler;


    private TravelerDocument(Builder b) {
        this.documentType = b.documentType;
        this.numberDocument = b.numberDocument;
        this.expireDate = b.expireDate;
        this.issuanceCountry = b.issuanceCountry;
        this.nationality = b.nationality;
    }

    public TravelerDocument() {

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

        public Builder expireDate(String expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public Builder issuanceCountry(String issuanceCountry) {
            this.issuanceCountry = issuanceCountry;
            return this;
        }

        public Builder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public TravelerDocument build() {
            return new TravelerDocument(this);
        }
    }
}
