package com.gui;

import com.client.ClientControl;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Controller
@Component
public class TopBar implements FxmlLoader{
    @Autowired
    private SpringFxmlLoader springFxmlLoader;

    @FXML
    public HBox parent;


    public void home(MainPanel mainPanel){
        mainPanel.getMainLoad().getChildren().add(loadUi("/MainPanel"));
    }


    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    public void minimize_btn(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
