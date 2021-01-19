package com.gui;

import com.ClientApplication;
import com.client.ClientControl;
import com.observer.LoginListener;
import com.observer.UserLoginObserver;
import com.repository.model.communication.LoginUserRequest;
import com.repository.model.communication.LoginUserResponse;
import com.repository.model.database.User;
import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.jfr.Event;
import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.Main;
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
        LoginUserRequest loginUserRequest = new LoginUserRequest(emailLabel.getText(), logPassw.getText());
        LoginUserResponse loginUserResponse = clientControl.loginUserCommunication(loginUserRequest);
        log.info(loginUserResponse.getStatus());
        if (loginUserResponse.getUser() != null) {
            update(loginUserResponse.getUser());
            mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
            System.out.println("Dsa");
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

    public void homeFunc(MouseEvent event) {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/MainPanel"));
    }

    public void exit_btn(MouseEvent event) {
        System.exit(0);
    }

    public void minimize_btn(MouseEvent event) {
        TopBar topbar = new TopBar();
        topbar.minimize_btn(event);
    }

}
