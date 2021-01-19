package com.gui;

import com.client.ClientControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Component
public class SearchPanel implements FxmlLoader{
    @Autowired
    private ClientControl clientControl;
    @Autowired
    private MainPanel mainPanel;

    @Autowired
    private SpringFxmlLoader springFxmlLoader;

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

    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    public void dragScene(MouseEvent event) {
        TopBar topbar = new TopBar();
        topbar.dragScene(event);
    }
}
