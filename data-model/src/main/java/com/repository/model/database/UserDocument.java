package com.repository.model.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserDocument implements Serializable {

    @Id
    @GeneratedValue

    private Integer documentId;
    private String documentType;
    private String expiryDate;
    private String issuanceCountry;
    private String nationality;
    private String holder;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    public UserDocument(Integer documentId, String documentType, String expiryDate, String issuanceCountry, String nationality, String holder) {
        this.documentId = documentId;
        this.documentType = documentType;
        this.expiryDate = expiryDate;
        this.issuanceCountry = issuanceCountry;
        this.nationality = nationality;
        this.holder = holder;
    }
}
