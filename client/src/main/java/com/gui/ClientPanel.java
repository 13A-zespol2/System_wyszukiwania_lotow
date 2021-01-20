package com.gui;

import com.client.ClientControl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Component
public class ClientPanel implements FxmlLoader{

    @Autowired
    private ClientControl clientControl;

    @Autowired
    private MainPanel mainPanel;

    @Autowired
    private SpringFxmlLoader springFxmlLoader;

    @FXML
    private Label clientData;
    @FXML
    private Label clientTickets;
    @FXML
    private Label clientEdit;

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

    public void toClientTickets(MouseEvent event) {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/tickets"));
    }

    public void toClientEdit(MouseEvent event) {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/editData"));
    }
}
