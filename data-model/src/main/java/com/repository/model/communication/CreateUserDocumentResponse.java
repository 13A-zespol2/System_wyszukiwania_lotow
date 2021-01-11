package com.repository.model.communication;

import com.repository.model.database.UserDocument;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class CreateUserDocumentResponse implements Serializable {

    private String status;
    private UserDocument userDocument;

    public CreateUserDocumentResponse(String status, UserDocument userDocument) {
        this.status = status;
        this.userDocument = userDocument;
    }

    public CreateUserDocumentResponse(String status) {
        this.status = status;
    }

    public CreateUserDocumentResponse(){

    }
}
