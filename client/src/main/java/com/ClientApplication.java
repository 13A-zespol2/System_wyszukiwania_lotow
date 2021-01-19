package com;

import com.gui.MainPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@SpringBootApplication
public class ClientApplication extends Application {
    private ConfigurableApplicationContext springContext;
    private AnchorPane rootNode;



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws IOException {
        springContext = SpringApplication.run(ClientApplication.class);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation((getClass().getResource("/MainPanel.fxml")));
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(rootNode, 960, 640);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }


}
