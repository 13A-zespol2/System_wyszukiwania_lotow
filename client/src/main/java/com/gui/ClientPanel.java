package com.gui;

import com.repository.model.communication.ClientDataRequest;
import com.repository.model.communication.ClientDataResponse;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
@Component
public class ClientPanel extends GuiPanel {

    private User user;


    @FXML
    private Label loggedname;

    @FXML
    private Label nameSurname;

    @FXML
    private Label email;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label birthDate;

    @FXML
    private Label nationality;

    @FXML
    private Label docType;

    @FXML
    private Label expiryDate;

    @FXML
    private Label docNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loggedname.setText(userLoginObserver.getUser().getEmail());
        email.setText(userLoginObserver.getUser().getEmail());


        ClientDataResponse clientDataResponse = clientControl.clientDataComunication(new ClientDataRequest(userLoginObserver.getUser()));

        if (clientDataResponse.getMyTraveler() != null) {
            String name = clientDataResponse.getMyTraveler().getName();
            String surname = clientDataResponse.getMyTraveler().getSurname();
            nameSurname.setText(name + " " + surname);
            phoneNumber.setText(String.valueOf(clientDataResponse.getMyTraveler().getTravelerPhone().getPhoneNumber()));
            birthDate.setText(clientDataResponse.getMyTraveler().getDateOfBirth());
            docType.setText(clientDataResponse.getTravelerDocument().getDocumentType());
            nationality.setText(clientDataResponse.getTravelerDocument().getNationality());
            expiryDate.setText(clientDataResponse.getTravelerDocument().getExpireDate());
            docNumber.setText(clientDataResponse.getTravelerDocument().getNumberDocument());


        }
    }

    public void toClientData() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
    }


    @Override
    public void update(User user) {
        this.user = user;
    }


}
