package com.repository.model.communication;

import com.repository.model.database.UserDocument;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUserDocumentRequest {

    private UserDocument userDocument;
    private Integer documentId;
    private String documentType;
    private String expiryDate;
    private String issuanceCountry;
    private String nationality;
    private String holder;

    public CreateUserDocumentRequest(){

    }

    public CreateUserDocumentRequest(UserDocument userDocument) {
        this.userDocument = userDocument;
    }

}
