package com.repository.model.communication;

import com.repository.model.database.UserCreditCard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUserCreditCardRequest {

    private Integer creditCardId;
    private Integer cardNumber;
    private Integer csvCode;
    private String expiryDate;
    private UserCreditCard userCreditCard;

    public CreateUserCreditCardRequest() {

    }

    public CreateUserCreditCardRequest(UserCreditCard userCreditCard){
        this.userCreditCard = userCreditCard;


    }
}
