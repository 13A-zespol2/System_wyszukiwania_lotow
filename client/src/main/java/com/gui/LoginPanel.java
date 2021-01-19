package com.gui;

import com.client.ClientControl;
import com.observer.LoginListener;
import com.observer.UserLoginObserver;
import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
@Component
public class LoginPanel implements InitializingBean, LoginListener, FxmlLoader {

    @Autowired
    private ClientControl clientControl;

    @Autowired
    private UserLoginObserver userLoginObserver;

    @Autowired
    private MainPanel mainPanel;
    @Autowired
    private SpringFxmlLoader springFxmlLoader;

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

        //TODO dodanie labela do wyswietlania informacji o bledzie lub
        mainPanel.getMainLoad().getChildren().add(loadUi("/Register"));
        System.out.println("Dsa");
     /*   LoginUserRequest loginUserRequest = new LoginUserRequest(emailLabel.getText(), logPassw.getText());
        LoginUserResponse loginUserResponse = clientControl.loginUserCommunication(loginUserRequest);
        log.info(loginUserResponse.getStatus());
        if (loginUserResponse.getUser() != null) {
            update(loginUserResponse.getUser());
        }*/

    }


    @Override
    public void update(User user) {
        userLoginObserver.loginNotify(user);
    }

    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }
}
