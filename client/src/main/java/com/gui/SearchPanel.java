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

    private User user;


    public void searchFlights() {
        SearchFlightRequest createSearchFlightRequest = new SearchFlightRequest();
        createSearchFlightRequest.setDestinationLocationCode(destinationLocationCode.getValue());
        createSearchFlightRequest.setOriginLocationCode(originLocationCode.getValue());
        createSearchFlightRequest.setDepartureDate(String.valueOf(departureDate.getValue()));
        if (isNumeric(adults.getText()))
            createSearchFlightRequest.setAdults(adults.getText());
        createSearchFlightRequest.setTravelClass(travelClass.getValue());

        /*SearchFlightRequest createUserRequest = new SearchFlightRequest("BER", "LIS", "2021-02-21", "1", "ECONOMY", "0", false, "");*/
        SearchFlightResponse searchFlightResponse = clientControl.searchFlight(createSearchFlightRequest);


        if (searchFlightResponse != null) {
            List<String> tList = searchFlightResponse.getTList();
            List<FlightOfferSearch> collect = tList.stream()
                    .map(e -> new Gson().fromJson(e, FlightOfferSearch.class))
                    .collect(Collectors.toList());
            showFlights(collect);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (userLoginObserver.getUser() != null) {
            loggedname.setText(userLoginObserver.getUser().getEmail());
        } else {
            lias.setText("Not logged in");
        }


        ObservableList<String> listCity = FXCollections.observableArrayList();
        for (Map.Entry<String, AirportCode> entry : AirportCode.getByIata().entrySet()) {
            listCity.add(entry.getValue().IATACode);
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

        col2.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode())));

        col3.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getIataCode())));

        col4.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt())));

        col5.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getAt())));

        col6.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getPrice().getTotal() + " €")));

        col7.setCellValueFactory(param -> new ReadOnlyStringWrapper(Arrays.stream(Arrays.stream(param.getValue().getTravelerPricings()).findFirst().get().getFareDetailsBySegment()).findFirst().get().getCabin()));

        tableView.setItems(list);
    }


    @Override
    public void update(User user) {
        this.user = user;
    }
}
