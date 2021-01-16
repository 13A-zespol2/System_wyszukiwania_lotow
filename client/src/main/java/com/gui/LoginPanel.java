package com.gui;

import com.client.ClientControl;
import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
@Component
public class LoginPanel implements InitializingBean {

    @Autowired
    ClientControl clientControl;


    @FXML
    private PasswordField logPassw;

    @FXML
    private TextField emailLabel;


    @Override
    public void afterPropertiesSet() {

    }


    public void logButton(MouseEvent mouseEvent) {
        if (emailLabel.getText().isEmpty())
            log.error("Pole email puste");
        if (logPassw.getText().isEmpty())
            log.error("Pole password puste");
        //TODO walidacja hasla osobna metoda do walidacji hasla
        //TODO podpiecie dekoratora
        //TODO dodanie labela do wyswietlania informacji o bledzie lub

        LoginUserRequest loginUserRequest = new LoginUserRequest(emailLabel.getText(), logPassw.getText());
        LoginUserResponse loginUserResponse = clientControl.loginUserCommunication(loginUserRequest);
        log.info(loginUserResponse.getStatus());
        System.out.println("Dsa");
    }
}
