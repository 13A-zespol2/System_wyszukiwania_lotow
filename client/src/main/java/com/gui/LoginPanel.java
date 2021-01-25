package com.gui;

import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
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

/**
 * Kontroler wyświetlający ekran logowania użytkownika i zarządzający nim.
 */
public class LoginPanel extends GuiPanel {

    @FXML
    public Label loginAfterReg;
    @FXML
    private PasswordField logPassw;
    @FXML
    private TextField emailLabel;
    @FXML
    private Label loginError;


    public LoginPanel() {
        super();
    }


    /**
     * Metoda wywołana po naciśnięciu przycisku logowania. Wykonuje się w niej walidacja wprowadzonych przez użytkownika danych.
     * W przypadku gdy dane są wprowadzone prawidłowo, przenosi nas do ekranu panelu klienta.
     */
    public void logButton() {
        if (emailLabel.getText().isEmpty()) {
            loginError.setText("Complete e-mail field!");
        }
        if (logPassw.getText().isEmpty()) {
            loginError.setText("Complete password field!");
        }
        if (logPassw.getText().isEmpty() && emailLabel.getText().isEmpty()) {
            loginError.setText("Complete required fields!");
        }

        LoginUserRequest loginUserRequest = new LoginUserRequest(emailLabel.getText(), logPassw.getText());
        LoginUserResponse loginUserResponse = clientControl.loginUserCommunication(loginUserRequest);
        log.info(loginUserResponse.getStatus());
        if ((loginUserResponse.getUser() == null) && (!emailLabel.getText().isEmpty()) && (!logPassw.getText().isEmpty())) {
            loginError.setText("Incorrect data. Try again!");
        }
        if (loginUserResponse.getUser() != null) {
            notifyGui(loginUserResponse.getUser());
            mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
