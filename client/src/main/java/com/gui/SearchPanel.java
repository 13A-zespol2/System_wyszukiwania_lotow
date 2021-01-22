package com.gui;

import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.Gson;
import com.repository.model.communication.SearchFlightRequest;
import com.repository.model.communication.SearchFlightResponse;
import com.repository.model.data.AirportCode;
import com.repository.model.database.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
@Controller
@Component
public class SearchPanel extends GuiPanel {


    @FXML
    public TableView<FlightOfferSearch> tableView;

    @FXML
    private ComboBox<String> originLocationCode;

    @FXML
    private ComboBox<String> destinationLocationCode;

    @FXML
    private DatePicker departureDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private CheckBox returnCheckbox;

    @FXML
    private ComboBox<String> travelClass;

    @FXML
    private TextField children;

    @FXML
    private TextField adults;


    @FXML
    private TableColumn<FlightOfferSearch, String> col1;

    @FXML
    private TableColumn<FlightOfferSearch, String> col2;

    @FXML
    private TableColumn<FlightOfferSearch, String> col3;

    @FXML
    private TableColumn<FlightOfferSearch, String> col4;

    @FXML
    private TableColumn<FlightOfferSearch, String> col5;

    @FXML
    private TableColumn<FlightOfferSearch, String> col6;

    @FXML
    private TableColumn<FlightOfferSearch, String> col7;

    @FXML
    private Label loggedname;

    @FXML
    private Label lias;

    String originToSend;
    String destToSend;

    private User user;
    String originValue;
    String destValue;
    @FXML
    private Label loginError;
    @FXML
    private Label loginError1;
    @FXML
    private Button search_SB1;

    public boolean searchFlights() {

        ObservableList<String> listByName = FXCollections.observableArrayList();
        ObservableList<String> listByIata = FXCollections.observableArrayList();
        for (Map.Entry<String, AirportCode> entry : AirportCode.getByIata().entrySet()) {
            listByName.add(entry.getValue().name());
            listByIata.add(entry.getValue().IATACode);
        }

        originValue = originLocationCode.getValue();
        destValue = destinationLocationCode.getValue();

        for (int a = 0; a < listByName.size(); a++) {
            if (originValue != null) {
                if (originValue.equals(listByName.get(a))) {
                    originToSend = listByIata.get(a);
                }
            }

            if (destValue != null) {
                if (destValue.equals(listByName.get(a))) {
                    destToSend = listByIata.get(a);
                }
            }
        }


        SearchFlightRequest createSearchFlightRequest = new SearchFlightRequest();
        createSearchFlightRequest.setOriginLocationCode(originToSend);
        createSearchFlightRequest.setDestinationLocationCode(destToSend);
        createSearchFlightRequest.setDepartureDate(String.valueOf(departureDate.getValue()));
        if (isNumeric(adults.getText()))
            createSearchFlightRequest.setAdults(adults.getText());
        createSearchFlightRequest.setTravelClass(travelClass.getValue());

        if ((originToSend == null) ||
                (destToSend == null) ||
                (departureDate.getValue() == null) ||
                (adults.getText().isEmpty()) ||
                (travelClass.getValue() == null)) {
            loginError.setText("Complete required fields!");
            return false;
        } else {
            if (Integer.parseInt(adults.getText()) > 5) {
                loginError.setText("Maximum number of tickets is 5!");
                return false;
            }
            loginError.setText("");
            SearchFlightResponse searchFlightResponse = clientControl.searchFlight(createSearchFlightRequest);
            if (searchFlightResponse != null) {
                List<String> tList = searchFlightResponse.getTList();
                if (tList.isEmpty()) {
                    loginError.setText("No flights found.");
                }
                List<FlightOfferSearch> collect = tList.stream()
                        .map(e -> new Gson().fromJson(e, FlightOfferSearch.class))
                        .collect(Collectors.toList());
                showFlights(collect);
            }
        }
        return true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if (userLoginObserver.getUser() != null) {
            loggedname.setText(userLoginObserver.getUser().getEmail());
        } else {
            lias.setText("Not logged in");
            search_SB1.setVisible(false);
            loginError1.setText("Register or LogIn if you want to buy a ticket.");
        }


        ObservableList<String> listCity = FXCollections.observableArrayList();
        for (Map.Entry<String, AirportCode> entry : AirportCode.getByIata().entrySet()) {
            listCity.add(entry.getValue().name());
        }

        originLocationCode.setItems(listCity);
        destinationLocationCode.setItems(listCity);


        ObservableList<String> classList = FXCollections.observableArrayList(List.of("ECONOMY", "PREMIUM_ECONOMY", "BUSINESS", "FIRST"));
        travelClass.setItems(classList);

    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void showFlights(List<FlightOfferSearch> tList) {
        ObservableList<FlightOfferSearch> list = FXCollections.observableArrayList();
        list.addAll(tList);

        col1.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId()));

        col2.setCellValueFactory(param -> new ReadOnlyStringWrapper(originValue + " (" + String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode()) + ")"));

        col3.setCellValueFactory(param -> new ReadOnlyStringWrapper(destValue + " (" + String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getIataCode()) + ")"));

        col4.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt()).substring(11)));

        col5.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getAt()).substring(11)));

        col6.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getPrice().getTotal() + " €")));

        col7.setCellValueFactory(param -> new ReadOnlyStringWrapper(Arrays.stream(Arrays.stream(param.getValue().getTravelerPricings()).findFirst().get().getFareDetailsBySegment()).findFirst().get().getCabin()));

        tableView.setItems(list);
    }


    @Override
    public void update(User user) {
        this.user = user;
    }
}
