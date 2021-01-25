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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public boolean validPassword(String password, String repeatPassword) {
        String passRegEx = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&*().]).{8,20}$";

        if ((password.matches(passRegEx)) && (repeatPassword.equals(password))) {
            return true;
        } else {
            validError.setText("password does not meet the requirements");
        }

        if (!password.equals(repeatPassword)) {
            validError.setText("Password not equal");
            return false;
        }
        return false;
    }

    private boolean validEmpty() {

        if (password.getText().isEmpty() || repeatPassword.getText().isEmpty() || name.getText().isEmpty() || surname.getText().isEmpty() ||
                birthDate.getText().isEmpty() || docType.getText().isEmpty() || docNumber.getText().isEmpty() || expDate.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
            validError.setText("Fill all fields");
            return false;

        }

        return true;
    }

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

    public boolean validPhone(String number) {

        Pattern p = Pattern.compile("^\\d{9}$");

        Matcher m = p.matcher(number);
        return (m.find() && m.group().equals(number));

    }

    public boolean validDocumentNumber(String docNumber) {
        String passRegEx = "^(?=.*\\d)(?=.*[A-Z]).{9}+$";

        if (docNumber.matches(passRegEx)) {
            return true;
        }
        validError.setText("Enter correct document number");
        return false;

    }


    public void editData() {

        if (validPassword(password.getText(), repeatPassword.getText()) && validEmpty() && validDate(birthDate.getText()) && validDate(expDate.getText()) && validPhone(phoneNumber.getText()) && validDocumentNumber(docNumber.getText())) {
            MyTraveler myTraveler = new MyTraveler.Builder().name(name.getText()).surname(surname.getText()).dateOfBirth(birthDate.getText()).build();
            TravelerDocument travelerDocument = new TravelerDocument.Builder().documentType(docType.getText()).numberDocument(docNumber.getText()).expireDate(expDate.getText()).build();
            TravelerPhone travelerPhone = new TravelerPhone.Builder().phoneNumber(Integer.parseInt(phoneNumber.getText())).build();

            ClientEditRequest clientDataRequestEdit = new ClientEditRequest(userLoginObserver.getUser(), myTraveler, travelerDocument, travelerPhone);
            clientDataRequestEdit.getUser().setPassword(password.getText());

            ClientEditResponse clientEditResponse = clientControl.clientEditCommunication(clientDataRequestEdit);
            validError.setText("");
            validError1.setText(clientEditResponse.getStatus());
        } else {
            validError.setText("Enter correct data");
        }
        if (!validPhone(phoneNumber.getText())) {
            validError.setText("Enter correct phone number");

        }

    }
}
