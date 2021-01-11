package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class UserCreditCard implements Serializable {

    @Id
    @GeneratedValue

    private Integer creditCardId;
    private Integer cardNumber;
    private Integer csvCode;
    private String expiryDate;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    public UserCreditCard(Integer creditCardId, Integer cardNumber, Integer csvCode, String expiryDate) {
        this.creditCardId = creditCardId;
        this.cardNumber = cardNumber;
        this.csvCode = csvCode;
        this.expiryDate = expiryDate;
    }
}
