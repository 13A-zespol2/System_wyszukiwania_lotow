package com.gui;


import com.repository.model.communication.ClientDataRequest;
import com.repository.model.communication.ClientDataResponse;
import com.repository.model.communication.ClientEditRequest;
import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import com.sun.xml.bind.v2.TODO;
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


        loggedname.setText(userLoginObserver.getUser().getEmail());

        new ClientDataRequest(userLoginObserver.getUser());
        ClientDataResponse clientDataResponse = clientControl.clientDataComunication(new ClientDataRequest(userLoginObserver.getUser()));

        if (clientDataResponse.getMyTraveler() != null) {
            name.setText(clientDataResponse.getMyTraveler().getName());
            surname.setText(clientDataResponse.getMyTraveler().getSurname());
            phoneNumber.setText(String.valueOf(clientDataResponse.getMyTraveler().getTravelerPhone().getPhoneNumber()));
            password.setText(clientDataResponse.getUser().getPassword());
            repeatPassword.setText(clientDataResponse.getUser().getPassword());
            if (clientDataResponse.getTravelerDocument() != null) {
                docType.setText(clientDataResponse.getTravelerDocument().getDocumentType());
                docNumber.setText(clientDataResponse.getTravelerDocument().getNumberDocument());
                birthDate.setText(clientDataResponse.getMyTraveler().getDateOfBirth());
                expDate.setText(clientDataResponse.getTravelerDocument().getExpireDate());
            }
        }
    }

    private boolean validPassword() {
        String passRegEx = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&*().]).{8,20}$";

        if ((password.getText().matches(passRegEx)) && (repeatPassword.getText().equals(password.getText()))) {
            return true;
        } else {
            validError.setText("password does not meet the requirements");
        }

        if (!password.getText().equals(repeatPassword.getText())) {
            validError.setText("Password not equal");
            return false;
        }
        return false;
    }

    private boolean validEmpty(){

        if(password.getText().isEmpty() || repeatPassword.getText().isEmpty() || name.getText().isEmpty() || surname.getText().isEmpty() ||
                birthDate.getText().isEmpty() || docType.getText().isEmpty() || docNumber.getText().isEmpty() || expDate.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
            validError.setText("Fill all fields");
            return false;

        }

        return true;
    }

    private boolean validDate(String strDate){
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        try {

            sdfrmt.parse(strDate);
        }
        catch (ParseException e) {

            validError.setText("Enter correct Birth or Expire date");

            return false;
        }
        return true;

    }

    private boolean validPhone(String number){

        Pattern p = Pattern.compile("^\\d{9}$");

        Matcher m = p.matcher(number);
        return (m.find() && m.group().equals(number));

    }

    private boolean validDocumentNumber(){
        String passRegEx = "^(?=.*\\d)(?=.*[A-Z]).{9}+$";

        if(docNumber.getText().matches(passRegEx)) {
            return true;
        }
        validError.setText("Enter correct document number");
        return false;

    }







    public void editData() {

        if(validPassword() && validEmpty() && validDate(birthDate.getText()) && validDate(expDate.getText()) && validPhone(phoneNumber.getText()) && validDocumentNumber()){
            validError.setText("");
            validError1.setText("Data changed");
            MyTraveler myTraveler = new MyTraveler.Builder().name(name.getText()).surname(surname.getText()).dateOfBirth(birthDate.getText()).build();
            TravelerDocument travelerDocument = new TravelerDocument.Builder().documentType(docType.getText()).numberDocument(docNumber.getText()).expireDate(expDate.getText()).build();
            TravelerPhone travelerPhone = new TravelerPhone.Builder().phoneNumber(Integer.parseInt(phoneNumber.getText())).build();

            ClientEditRequest clientDataRequestEdit = new ClientEditRequest(userLoginObserver.getUser(), myTraveler, travelerDocument, travelerPhone);
            clientDataRequestEdit.getUser().setPassword(password.getText());

            clientControl.clientEditCommunication(clientDataRequestEdit);
        }
        else
        {
            //validError.setText("Enter correct data");
        }
        if(!validPhone(phoneNumber.getText())) {
            validError.setText("Enter correct phone number");

        }

/*        MyTraveler myTraveler = new MyTraveler.Builder().name(name.getText()).surname(surname.getText()).dateOfBirth(birthDate.getText()).build();
        TravelerDocument travelerDocument = new TravelerDocument.Builder().documentType(docType.getText()).numberDocument(docNumber.getText()).expireDate(expDate.getText()).build();
        TravelerPhone travelerPhone = new TravelerPhone.Builder().phoneNumber(Integer.parseInt(phoneNumber.getText())).build();

        ClientEditRequest clientDataRequestEdit = new ClientEditRequest(userLoginObserver.getUser(), myTraveler, travelerDocument, travelerPhone);
        clientDataRequestEdit.getUser().setPassword(password.getText());

        clientControl.clientEditCommunication(clientDataRequestEdit);*/
    }
}
