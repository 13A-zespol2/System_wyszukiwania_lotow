package com.gui;

import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Component

/**
 * Kontroler zarządzający głównym ekranem aplikacji widoczny zaraz po jej uruchomieniu.
 */
public class MainPanel extends GuiPanel {

    public Button login_button;

    public Button register_button;

    public Button clientPanelBtn;


    @FXML
    private AnchorPane mainLoad;
    private User user;


    /**
     * Metoda wywołana po wciśnięciu przycisku ,,Search flight". Przenosi nas do ekranu wyszukiwania lotów.
     */
    public void searchFlights() {

        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/searchPanel");
        mainLoad.getChildren().add(root);
    }


    /**
     * Metoda wywołana po wciśnięciu przycisku ,,Register". Przenosi nas do ekranu rejestracji użytkownika.
     */
    public void register() {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/Register");
        mainLoad.getChildren().add(root);
    }

    /**
     * Metoda wywołana po wciśnięciu przycisku ,,LogIn". Przenosi nas do ekranu logowania użytkownika.
     */
    public void login() {

        if (user == null) {
            if (!mainLoad.getChildren().isEmpty()) {
                mainLoad.getChildren().clear();
            }
            AnchorPane root = loadUi("/LogIn");
            mainLoad.getChildren().add(root);

        }

    }

    public AnchorPane getMainLoad() {
        return mainLoad;
    }

    @Override
    public void update(User user) {
        this.user = user;
    }

    /**
     * Metoda służąca do ukrycia przycisku rejestracji i logowania w momencie, gdy użytkownik jest już zalogowany.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (userLoginObserver.getUser() != null) {
            login_button.setVisible(false);
            register_button.setVisible(false);
            clientPanelBtn.setVisible(true);
        }
    }
}


