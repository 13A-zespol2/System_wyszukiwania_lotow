package com.gui;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
/**
 * Klasa odpowiadająca za prawidłowe ładowanie plików FXML.
 */
public class SpringFxmlLoader {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Metoda ładująca pliki FXML do kontrolera głównego
     *
     * @param url
     * @param params
     * @return
     */
    public Object load(String url, Object... params) {
        try (InputStream fxmlStream = SpringFxmlLoader.class.getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass, params));
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}