package com.gui;

import com.client.ClientControl;
import com.observer.LoginListener;
import com.observer.UserLoginObserver;
import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    @FXML
    private Label loginError;


    @Override
    public void afterPropertiesSet() {

    }


    public void logButton() {
        if (emailLabel.getText().isEmpty()) {
            loginError.setText("Proszę wypełnić pole e-mail!");
        }
        if (logPassw.getText().isEmpty()) {
            loginError.setText("Proszę wypełnić hasło!");
        }
        if(logPassw.getText().isEmpty() && emailLabel.getText().isEmpty()){
            loginError.setText("Pola nie mogą być puste!");
        }

        //TODO dodanie labela do wyswietlania informacji o bledzie lub

        LoginUserRequest loginUserRequest = new LoginUserRequest(emailLabel.getText(), logPassw.getText());
        LoginUserResponse loginUserResponse = clientControl.loginUserCommunication(loginUserRequest);
        log.info(loginUserResponse.getStatus());
        if((loginUserResponse.getUser() == null) && (!emailLabel.getText().isEmpty()) &&  (!logPassw.getText().isEmpty())){
            loginError.setText("Błędne dane. Spróbuj jeszcze raz.");
        }
        if (loginUserResponse.getUser() != null) {
            update(loginUserResponse.getUser());
            mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
            //TODO Jestes zalogowany jako:
        }

    }


    @Override
    public void update(User user) {
        userLoginObserver.loginNotify(user);
    }

    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

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

}
