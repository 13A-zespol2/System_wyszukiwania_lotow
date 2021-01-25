package com.gui;


import com.repository.model.communication.ClientDataRequest;
import com.repository.model.communication.ClientDataResponse;
import com.repository.model.communication.ClientEditRequest;
import com.repository.model.communication.ClientEditResponse;
import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Controller
@Component

/** Kontroler zarządzający widokiem edycji danych osobistych i zmiany hasła użytkownika. */
public class ClientEdit extends GuiPanel {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField docType;

    @FXML
    private TextField docNumber;

    @FXML
    private TextField expDate;

    @FXML
    private TextField birthDate;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;
    @FXML
    private Label loggedname;
    @FXML
    private Label validError;

    @FXML
    private Label validError1;


    @Override
    public void update(User user) {

    }

    /**
     * Metoda pobierająca i ładująca dane z bazy danych do formatki edycji.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        docType.setText("PASSPORT");
        ClientDataResponse clientDataResponse = clientControl.clientDataComunication(new ClientDataRequest(userLoginObserver.getUser()));

        loggedname.setText(userLoginObserver.getUser().getEmail());
        password.setText(userLoginObserver.getUser().getPassword());
        repeatPassword.setText(userLoginObserver.getUser().getPassword());

        if (clientDataResponse.getMyTraveler() != null) {
            name.setText(clientDataResponse.getMyTraveler().getName());
            surname.setText(clientDataResponse.getMyTraveler().getSurname());
            phoneNumber.setText(String.valueOf(clientDataResponse.getMyTraveler().getTravelerPhone().getPhoneNumber()));

            if (clientDataResponse.getTravelerDocument() != null) {
                docType.setText(clientDataResponse.getTravelerDocument().getDocumentType());
                docNumber.setText(clientDataResponse.getTravelerDocument().getNumberDocument());
                birthDate.setText(clientDataResponse.getMyTraveler().getDateOfBirth());
                expDate.setText(clientDataResponse.getTravelerDocument().getExpireDate());
            }
        }
    }

    /**
     * Metoda służąca do walidacji hasła. Przyjmuje parametry stringów ,,hasło" i ,,powtórzone hasło".
     * Walidacja wymaga aby hasło zawierało od 8 do 20 znaków, minimum jedną dużą literę, jeden znak specjalny i jedną cyfrę. (np. zaq1@WSX).
     *
     * @param password       Hasło
     * @param repeatPassword Powtórzone hasło
     * @return Metoda ta zwraca true w przypadku gdy walidacja się powiedzie, i false w przypadku błędu.
     */
    public boolean validPassword(String password, String repeatPassword) {
        String passRegEx = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&*().]).{8,20}$";

        if ((password.matches(passRegEx)) && (repeatPassword.equals(password))) {
            return true;
        } else {
            validError.setText("Password does not meet the requirements!");
        }

        if (!password.equals(repeatPassword)) {
            validError.setText("Passwords are not equal!");
            return false;
        }
        return false;
    }

    /**
     * Metoda sprawdzająca, czy pola do wypełnienia przez użytkownika nie pozostały puste.
     *
     * @return Zwraca false, w przypadku gdy któreś pole nie zostało wypełnione. W innym przypadku zwraca true.
     */
    private boolean validEmpty() {

        if (password.getText().isEmpty() || repeatPassword.getText().isEmpty() || name.getText().isEmpty() || surname.getText().isEmpty() ||
                birthDate.getText().isEmpty() || docType.getText().isEmpty() || docNumber.getText().isEmpty() || expDate.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
            validError.setText("Fill all fields");
            return false;
        }
        return true;
    }


    /**
     * Metoda walidująca poprawność wpisanej daty, aby była w formacie wymaganym przez API Amadeus. (Prawidłowo np.: 2000-08-08).
     *
     * @param strDate Wartość uzupełnionego przez użytkownika inputa.
     * @return Zwraca false w przypadku, gdy data jest wpisana w nieprawidłowej kolejności. W innym przypadku zwraca true.
     */
    public boolean validDate(String strDate) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        try {
            sdfrmt.parse(strDate);
        } catch (ParseException e) {
            validError.setText("Enter correct Birth or Expire date");
            return false;
        }
        return true;
    }


    /**
     * Metoda sprawdza czy numer telefonu wpisany przez użytkownika zawiera dokładnie 9 znaków numerycznych.
     *
     * @param number Wartość uzupełnionego przez użytkownika inputa.
     * @return
     */
    public boolean validPhone(String number) {

        Pattern p = Pattern.compile("^\\d{9}$");

        Matcher m = p.matcher(number);
        return (m.find() && m.group().equals(number));
    }


    /**
     * Metoda sprawdzająca czy numer dokumentu jest wypełniony prawidłowo (9 znaków, minimum jedna cyfra i minimum jedna duża litera, np: AAABBBCC1).
     *
     * @param docNumber Wartość uzupełnionego przez użytkownika inputa.
     * @return
     */
    public boolean validDocumentNumber(String docNumber) {
        String passRegEx = "^(?=.*\\d)(?=.*[A-Z]).{9}+$";

        if (docNumber.matches(passRegEx)) {
            return true;
        }
        validError.setText("Enter correct document number");
        return false;

    }


    /**
     * Metoda wywołana po naciśnięciu przycisku ,,Edit". Wywołuje ona po kolei walidację wszystkich wymaganych pól.
     * W przypadku gdy wszystkie zwrócą true, dane w bazie są nadpisywane.
     */
    public void editData() {

        if (validPassword(password.getText(), repeatPassword.getText()) && validPhone(phoneNumber.getText()) && validDocumentNumber(docNumber.getText()) && validEmpty() && validDate(birthDate.getText()) && validDate(expDate.getText()) && validPhone(phoneNumber.getText())) {
            MyTraveler myTraveler = new MyTraveler.Builder().name(name.getText()).surname(surname.getText()).dateOfBirth(birthDate.getText()).build();
            TravelerDocument travelerDocument = new TravelerDocument.Builder().documentType("PASSPORT").numberDocument(docNumber.getText()).expireDate(expDate.getText()).build();
            TravelerPhone travelerPhone = new TravelerPhone.Builder().phoneNumber(Integer.parseInt(phoneNumber.getText())).build();

            ClientEditRequest clientDataRequestEdit = new ClientEditRequest(userLoginObserver.getUser(), myTraveler, travelerDocument, travelerPhone);
            clientDataRequestEdit.getUser().setPassword(password.getText());

            ClientEditResponse clientEditResponse = clientControl.clientEditCommunication(clientDataRequestEdit);
            validError.setText("");
            validError1.setText(clientEditResponse.getStatus());
        }


        if (!validPhone(phoneNumber.getText())) {
            validError.setText("Enter correct phone number.");
        }

    }
}
