package com.gui;


import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
@Component
public class ClientTickets extends GuiPanel {

    @FXML
    private Label clientData;
    @FXML
    private Label clientTickets;
    @FXML
    private Label clientEdit;
    @FXML
    private Label loggedname;


    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    public void toClientData() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
    }

    public void toClientTickets() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/tickets"));
    }

    public void toClientEdit() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/editData"));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLoginObserver.addObserver(this);
        loggedname.setText(userLoginObserver.getUser().getEmail());
    }
}
