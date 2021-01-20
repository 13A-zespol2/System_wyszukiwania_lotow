package com.gui;

import com.client.ClientControl;
import com.observer.LoginObserver;
import com.observer.UserLoginObserver;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GuiPanel implements Initializable, LoginObserver {
    @Autowired
    protected ClientControl clientControl;

    @Autowired
    protected UserLoginObserver userLoginObserver;

    @Autowired
    protected MainPanel mainPanel;

    @Autowired
    protected SpringFxmlLoader springFxmlLoader;


    protected GuiPanel() {
    }

    public void exit_btn() {
        System.exit(0);
    }


    protected AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    public void minimize_btn(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void homeFunc() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/MainPanel"));
    }

    public void toLoginPanel() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/LogIn"));
    }

    public void toClientData() {
        if (userLoginObserver.getUser() != null) {
            mainPanel.getMainLoad().getChildren().clear();
            mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
        } else {
            toLoginPanel();
        }
    }

    public void toClientTickets() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/tickets"));
    }

    public void toClientEdit() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/editData"));
    }

    public void toSearch() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/searchPanel"));
    }

    public void logOut() {
        if (userLoginObserver.getUser() != null) {
            userLoginObserver.loginNotify(null);
        }
        homeFunc();
    }
}
