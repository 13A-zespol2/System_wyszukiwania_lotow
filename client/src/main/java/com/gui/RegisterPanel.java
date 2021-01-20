package com.gui;

import com.client.ClientControl;
import com.repository.model.communication.RegisterUserRequest;
import com.repository.model.communication.RegisterUserResponse;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Component
public class RegisterPanel implements FxmlLoader{
    @Autowired
    private ClientControl clientControl;

    @Autowired
    private MainPanel mainPanel;

    @Autowired
    private SpringFxmlLoader springFxmlLoader;

    @FXML
    private PasswordField passwordInput;
    @FXML
    private PasswordField repeatpasswordInput;
    @FXML
    private TextField emailInput;

    public void homeFunc() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/MainPanel"));
    }

    public void exit_btn() {
        System.exit(0);
    }

    public void minimize_btn(MouseEvent event) {
        TopBar topbar = new TopBar();
        topbar.minimize_btn(event);
    }

    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }


    public boolean validPassword(){
        String passRegEx = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&*().]).{8,20}$";

        if(passwordInput.getText().matches(passRegEx)){
            if(repeatpasswordInput.getText().equals(passwordInput.getText())) {
                log.info("HASLO PRAWIDLOWE");
                return true;
            }
        }
        log.info("HASLO NIEPRAWIDLOWE");
        return false;
    }

   public boolean validEmail(){
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if(emailInput.getText().matches(regex)) {
            log.info("EMAIL PRAWIDLOWY");
            return true;
        }

            log.info("EMAIL NIEPRAWIDLOWY");
            return false;

    }

    public void regButton(){


        if(validPassword() && validEmail()){
            RegisterUserRequest registerUserRequest = new RegisterUserRequest(emailInput.getText(), passwordInput.getText());
            RegisterUserResponse registerUserResponse = clientControl.registerUserCommunication(registerUserRequest);
            log.info(registerUserResponse.getStatus());
        }
        else{

            log.info("NIEPRAWIDLOWE DANE");

        }





    }
}
