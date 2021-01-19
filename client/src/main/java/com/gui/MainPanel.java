package com.gui;

import com.client.ClientControl;
import com.observer.LoginListener;
import com.observer.UserLoginObserver;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.*;

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
    private Button login_button;
    private Button register_button;
    private Button search_button;
    private HBox parent;
    private Label minimize_btn;


    public void searchFlights(MouseEvent mouseEvent) {
    }

    public void exit_btn(MouseEvent mouseEvent) {
    }

    public void minimize_btn(MouseEvent mouseEvent) {
    }

    public void register(MouseEvent mouseEvent) {
    }

    public void login(MouseEvent mouseEvent) {

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
