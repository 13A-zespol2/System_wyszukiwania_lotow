package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Parent parent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(parent, 1024, 768);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void init() throws IOException {
        ConfigurableApplicationContext configurableWebApplicationContext = SpringApplication.run(ClientApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(configurableWebApplicationContext::getBean);
        fxmlLoader.setLocation((getClass().getResource("/LogIn.fxml")));
        parent = fxmlLoader.load();
    }


}
