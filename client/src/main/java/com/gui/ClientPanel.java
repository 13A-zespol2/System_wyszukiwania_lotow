package com.gui;

import com.repository.model.communication.ClientDataRequest;
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

    public void toClientData() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
    }


    @Override
    public void update(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLoginObserver.addObserver(this);
        loggedname.setText(userLoginObserver.getUser().getEmail());
        clientControl.clientDataComunication(new ClientDataRequest(userLoginObserver.getUser(), null, null, null));
    }
}
