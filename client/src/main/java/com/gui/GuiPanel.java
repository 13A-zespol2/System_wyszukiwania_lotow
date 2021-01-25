package com.gui;

import com.client.ClientControl;
import com.observer.LoginObserver;
import com.observer.UserStoringObserver;
import com.repository.model.database.User;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Abstrakcyjna klasa. Wszystkie widoki w aplikacji dziedziczą po niej. Zawiera metody dostępne dla wszystkich pozostałych kontrolerów.
 */
public abstract class GuiPanel implements Initializable, LoginObserver {
    @Autowired
    protected ClientControl clientControl;

    @Autowired
    protected UserStoringObserver userLoginObserver;

    @Autowired
    protected MainPanel mainPanel;

    @Autowired
    protected SpringFxmlLoader springFxmlLoader;
    @Autowired
    private List<LoginObserver> loginObserverList;

    protected GuiPanel() {
    }


    /**
     * Metoda służąca do obsługi przycisku zamykania aplikacji.
     */
    public void exit_btn() {
        System.exit(0);
    }


    /**
     * Metoda służąca do ładowania kolejnych widoków.
     *
     * @param ui String przekazujący nazwę pliku fxml
     * @return Zwraca pożądaną scenę / widok.
     */
    protected AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }


    /**
     * Metoda służąca do minimalizacji okna aplikacji.
     *
     * @param event
     */
    public void minimize_btn(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    /**
     * Metoda obsługi przycisku przenoszącego do ekranu głównego aplikacji.
     */
    public void homeFunc() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/MainPanel"));
    }


    /**
     * Metoda przenosząca do ekranu logowania użytkownika.
     */
    public void toLoginPanel() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/LogIn"));
    }


    /**
     * Metoda przenosząca do panelu klienta po zalogowaniu użytkownika.
     */
    public void toClientData() {
        if (userLoginObserver.getUser() != null) {
            mainPanel.getMainLoad().getChildren().clear();
            mainPanel.getMainLoad().getChildren().add(loadUi("/clientPanel"));
        } else {
            toLoginPanel();
        }
    }


    /**
     * Metoda przenosząca do ekranu wyświetlającego bilety zarezerwowane przez użytkownika.
     */
    public void toClientTickets() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/tickets"));
    }


    /**
     * Metoda przenosząca do ekranu edycji użytkownika.
     */
    public void toClientEdit() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/editData"));
    }


    /**
     * Metoda przenosząca do ekranu wyszukiwania lotów.
     */
    public void toSearch() {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/searchPanel"));
    }


    /**
     * Metoda służąca do obsługi ikonki wylogowania użytkownika.
     */
    public void logOut() {
        if (userLoginObserver.getUser() != null) {
            notifyGui(null);
        }
        homeFunc();
    }


    /**
     * Metoda do wysyłania notyfikacji na temat zalogowania się użytkownika. Wykorzystywana we wzorcu projektowym ,,Observer".
     *
     * @param user
     */
    protected void notifyGui(User user) {
        loginObserverList.forEach(e -> e.update(user));
    }


}
