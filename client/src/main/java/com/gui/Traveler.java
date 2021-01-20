package com.gui;


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
public class Traveler extends GuiPanel {
    @FXML
    private Label clientData;
    @FXML
    private Label clientTickets;
    @FXML
    private Label clientEdit;

    @FXML
    private Label loggedname;

    @Override
    public void update(User user) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLoginObserver.addObserver(this);
        loggedname.setText(userLoginObserver.getUser().getEmail());
    }
}
