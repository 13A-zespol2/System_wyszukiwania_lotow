package com;

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

/**
 * Główna klasa uruchamiająca aplikację klienta. Zawiera wszystkie parametry startowe aplikacji.
 */
public class ClientApplication extends Application {
    private ConfigurableApplicationContext springContext;
    private AnchorPane rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda ładująca główny ekran aplikacji. Tworzy obiekt klasy FXMLLoader odpowiadający za ładowanie ekranu głównego.
     *
     * @throws IOException
     */
    @Override
    public void init() throws IOException {
        springContext = SpringApplication.run(ClientApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation((getClass().getResource("/MainPanel.fxml")));
        rootNode = fxmlLoader.load();
    }


    /**
     * Metoda do ustawiania sceny, rozdzielczości aplikacji, ładująca plik ze stylami. Umożliwia ona również poruszanie oknem aplikacji.
     *
     * @param primaryStage
     */
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(rootNode, 960, 640);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        rootNode.setOnMousePressed(pressEvent -> {
            rootNode.setOnMouseDragged(dragEvent -> {
                primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    /**
     * Metoda zatrzymująca aplikację.
     */
    @Override
    public void stop() {
        springContext.stop();
    }


}
