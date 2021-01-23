package com.gui;


import com.repository.model.communication.ClientDataRequest;
import com.repository.model.communication.ClientDataResponse;
import com.repository.model.communication.ClientEditRequest;
import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
@Component
public class ClientEdit extends GuiPanel {


    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField docType;

    @FXML
    private TextField docNumber;

    @FXML
    private TextField expDate;
    @FXML
    private TextField birthDate;
    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;
    @FXML
    private Label loggedname;


    @Override
    public void update(User user) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        loggedname.setText(userLoginObserver.getUser().getEmail());

        new ClientDataRequest(userLoginObserver.getUser());
        ClientDataResponse clientDataResponse = clientControl.clientDataComunication(new ClientDataRequest(userLoginObserver.getUser()));

        if (clientDataResponse.getMyTraveler() != null) {
            name.setText(clientDataResponse.getMyTraveler().getName());
            surname.setText(clientDataResponse.getMyTraveler().getSurname());
            phoneNumber.setText(String.valueOf(clientDataResponse.getMyTraveler().getTravelerPhone().getPhoneNumber()));
            password.setText(clientDataResponse.getUser().getPassword());
            if (clientDataResponse.getTravelerDocument() != null) {
                docType.setText(clientDataResponse.getTravelerDocument().getDocumentType());
                docNumber.setText(clientDataResponse.getTravelerDocument().getNumberDocument());
                birthDate.setText(clientDataResponse.getMyTraveler().getDateOfBirth());
                expDate.setText(clientDataResponse.getTravelerDocument().getExpireDate());
            }
        }
    }

    public void editData() {

        MyTraveler myTraveler = new MyTraveler.Builder().name(name.getText()).surname(surname.getText()).dateOfBirth(birthDate.getText()).build();
        TravelerDocument travelerDocument = new TravelerDocument.Builder().documentType(docType.getText()).numberDocument(docNumber.getText()).expireDate(expDate.getText()).build();
        TravelerPhone travelerPhone = new TravelerPhone.Builder().phoneNumber(Integer.parseInt(phoneNumber.getText())).build();

        ClientEditRequest clientDataRequestEdit = new ClientEditRequest(userLoginObserver.getUser(), myTraveler, travelerDocument, travelerPhone);
        clientDataRequestEdit.getUser().setPassword(password.getText());

        clientControl.clientEditCommunication(clientDataRequestEdit);

    }
}
