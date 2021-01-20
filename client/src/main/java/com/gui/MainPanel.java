package com.gui;

import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Component
public class MainPanel extends GuiPanel {

    public Button login_button;

    public Button register_button;

    public Button clientPanelBtn;

    @Getter

    @FXML
    private AnchorPane mainLoad;
    private User user;

    public void searchFlights() {

        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/searchPanel");
        mainLoad.getChildren().add(root);


    }

    public void exit_btn() {
        System.exit(0);
    }


    public void register() {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/Register");
        mainLoad.getChildren().add(root);
    }


    public void login() {

        if (user == null) {
            if (!mainLoad.getChildren().isEmpty()) {
                mainLoad.getChildren().clear();
            }
            AnchorPane root = loadUi("/LogIn");
            mainLoad.getChildren().add(root);
            userLoginObserver.deleteObserver(this);

        }

    }


    @Override
    public void update(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLoginObserver.addObserver(this);
        if (userLoginObserver.getUser() != null)
            login_button.setVisible(false);
            register_button.setVisible(false);
            clientPanelBtn.setVisible(true);
        }
    }


}
