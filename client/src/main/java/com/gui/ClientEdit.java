package com.gui;


import com.repository.model.communication.ClientDataRequest;
import com.repository.model.communication.ClientDataResponse;
import com.repository.model.communication.RegisterUserRequest;
import com.repository.model.communication.RegisterUserResponse;
import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
@Component
public class ClientEdit extends GuiPanel {

    private User user;
    private MyTraveler myTraveler;
    private TravelerDocument travelerDocument;
    private TravelerPhone travelerPhone;
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

 /*       userLoginObserver.addObserver(this);
        loggedname.setText(userLoginObserver.getUser().getEmail());

        ClientDataResponse clientDataResponse = clientControl.clientDataComunication(new ClientDataRequest(userLoginObserver.getUser()));

        name.setText(clientDataResponse.getMyTraveler().getName());
        surname.setText(clientDataResponse.getMyTraveler().getSurname());
        phoneNumber.setText(String.valueOf(clientDataResponse.getMyTraveler().getTravelerPhone().getPhoneNumber()));
        docType.setText(clientDataResponse.getTravelerDocument().getDocumentType());
        docNumber.setText(clientDataResponse.getTravelerDocument().getNumberDocument());*/


     }

    public void editData(MouseEvent mouseEvent) {


        name.getText();
        surname.getText();
        phoneNumber.getText();
        docType.getText();
        docNumber.getText();
        password.getText();
        repeatPassword.getText();

        System.out.println(name.getText());

/*        ClientDataRequest clientDataRequestEdit = new ClientDataRequest(user, myTraveler, travelerDocument, travelerPhone);
        ClientDataResponse clientDataResponseEdit = clientControl.clientDataComunication(clientDataRequestEdit);*/

        /*user = new User(password.getText());
        myTraveler = new MyTraveler(name.getText(), surname.getText());
        travelerDocument = new TravelerDocument(docNumber.getText(), docType.getText());
        travelerPhone = new TravelerPhone(Integer.parseInt(phoneNumber.getText()));
        System.out.println(myTraveler.getName() + myTraveler.getSurname());
        ClientDataRequest clientDataRequestEdit = new ClientDataRequest(user, myTraveler, travelerDocument, travelerPhone);
        ClientDataResponse clientDataResponse1 = clientControl.clientDataComunication(clientDataRequestEdit);*/
    }
}
