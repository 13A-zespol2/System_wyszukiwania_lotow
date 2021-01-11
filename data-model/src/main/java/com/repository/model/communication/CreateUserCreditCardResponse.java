package com.repository.model.communication;

import com.repository.model.database.UserCreditCard;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class CreateUserCreditCardResponse implements Serializable {
    private String status;
    private UserCreditCard userCreditCard;

    public CreateUserCreditCardResponse(String status, UserCreditCard userCreditCard) {
        this.status = status;
        this.userCreditCard = userCreditCard;
    }

    public CreateUserCreditCardResponse(String status) {
        this.status = status;
    }

    public CreateUserCreditCardResponse(){

    }
}
