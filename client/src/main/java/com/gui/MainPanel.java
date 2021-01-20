package com.gui;

import com.client.ClientControl;
import com.observer.LoginListener;
import com.observer.UserLoginObserver;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class MainPanel implements FxmlLoader, LoginListener {

    @Autowired
    private ClientControl clientControl;

    @Autowired
    private SpringFxmlLoader springFxmlLoader;

    @Autowired
    private UserLoginObserver userLoginObserver;
    @Getter
    @FXML
    private AnchorPane mainLoad;

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

    public void minimize_btn(MouseEvent event) {
        TopBar topbar = new TopBar();
        topbar.minimize_btn(event);
    }

    public void register() {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/Register");
        mainLoad.getChildren().add(root);
    }

    public void login() {

        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/LogIn");
        mainLoad.getChildren().add(root);


    }


    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    @Override
    public void update(User user) {

    }

}
