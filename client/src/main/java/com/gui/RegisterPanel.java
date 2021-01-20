package com.gui;

import com.repository.model.communication.RegisterUserRequest;
import com.repository.model.communication.RegisterUserResponse;
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
public class RegisterPanel extends GuiPanel {
    @FXML
    private PasswordField passwordInput;
    @FXML
    private PasswordField repeatpasswordInput;
    @FXML
    private TextField emailInput;
    @FXML
    private Label registerError;
    @FXML
    private Label loginAfterReg;


    public boolean validPassword() {
        String passRegEx = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&*().]).{8,20}$";

        if ((passwordInput.getText().matches(passRegEx)) && (repeatpasswordInput.getText().equals(passwordInput.getText()))) {
            return true;
        } else {
            registerError.setText("Nieprawidłowe hasło!");
        }

        if (passwordInput.getText().isEmpty() || repeatpasswordInput.getText().isEmpty()) {
            registerError.setText("Wypełnij puste pola!");
            return false;
        }

        if (!repeatpasswordInput.getText().equals(passwordInput.getText())) {
            registerError.setText("Hasła są niezgodne!");
            return false;
        }
        return false;
    }

    public boolean validEmail() {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if (emailInput.getText().matches(regex)) {
            return true;
        } else {
            registerError.setText("Nieprawidłowy e-mail!");
        }

        if (emailInput.getText().isEmpty()) {
            registerError.setText("Wypełnij puste pola!");

            return false;
        }
        return false;
    }

    public void regButton() throws InterruptedException {

        if (validPassword() && validEmail()) {
/*            registerError.setVisible(false);
            loginAfterReg.setText("Rejestracja przebiegła pomyślnie. Zaloguj się.");*/
            RegisterUserRequest registerUserRequest = new RegisterUserRequest(emailInput.getText(), passwordInput.getText());
            RegisterUserResponse registerUserResponse = clientControl.registerUserCommunication(registerUserRequest);

            toLoginPanel();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
