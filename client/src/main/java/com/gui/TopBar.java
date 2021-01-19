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
    private ClientControl clientControl;

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

    public void dragScene(MouseEvent event) {
        /*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double xOffSet = 0;
        double yOffSet = 0;
        parent.setOnMousePressed((event) -> {
            double xOffSet = event.getSceneX();
            double yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() - xOffSet);
           stage.setY(event.getScreenY() - yOffSet);
            stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
            stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((event) -> {
            stage.setOpacity(1.0f);
        });*/
    }
}
