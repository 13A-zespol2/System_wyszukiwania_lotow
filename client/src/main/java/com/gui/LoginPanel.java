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
public class LoginPanel extends GuiPanel {

    @FXML
    private PasswordField logPassw;
    @FXML
    private TextField emailLabel;
    @FXML
    private Label loginError;
    @FXML
    public Label loginAfterReg;

    public LoginPanel() {
        super();
    }


    public void logButton() {
        if (emailLabel.getText().isEmpty()) {
            loginError.setText("Proszę wypełnić pole e-mail!");
        }
        if (logPassw.getText().isEmpty()) {
            loginError.setText("Proszę wypełnić hasło!");
        }
        if (logPassw.getText().isEmpty() && emailLabel.getText().isEmpty()) {
            loginError.setText("Pola nie mogą być puste!");
        }

        //TODO dodanie labela do wyswietlania informacji o bledzie lub

        LoginUserRequest loginUserRequest = new LoginUserRequest(emailLabel.getText(), logPassw.getText());
        LoginUserResponse loginUserResponse = clientControl.loginUserCommunication(loginUserRequest);
        log.info(loginUserResponse.getStatus());
        if ((loginUserResponse.getUser() == null) && (!emailLabel.getText().isEmpty()) && (!logPassw.getText().isEmpty())) {
            loginError.setText("Błędne dane. Spróbuj jeszcze raz.");
        }
        if (loginUserResponse.getUser() != null) {
            update(loginUserResponse.getUser());
            userLoginObserver.loginNotify(loginUserResponse.getUser());
            mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));

            loginError.setText("ZALOGOWANO JAKO: " + loginUserResponse.getUser().getEmail());
            //TODO Jestes zalogowany jako:
        }

    }


    @Override
    public void update(User user) {
        userLoginObserver.loginNotify(user);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
