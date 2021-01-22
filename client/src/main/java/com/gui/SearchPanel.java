package com.gui;

import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.Gson;
import com.repository.model.communication.ReservationFlightRequest;
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
import java.time.LocalDate;
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
    private ComboBox<String> travelClass;

    @FXML
    private Spinner adults;

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



    public void searchFlights() {

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

        if ((originToSend == null) || (destToSend == null) ||
           (departureDate.getValue() == null) || (adults.getValue() == null) ||
           (travelClass.getValue() == null)) {
            loginError.setText("Complete required fields!");
        }else{
            loginError.setText("");

            SearchFlightRequest createSearchFlightRequest = new SearchFlightRequest();

            createSearchFlightRequest.setOriginLocationCode(originToSend);
            createSearchFlightRequest.setDestinationLocationCode(destToSend);
            createSearchFlightRequest.setDepartureDate(String.valueOf(departureDate.getValue()));
            createSearchFlightRequest.setAdults(String.valueOf(adults.getValue()));
            createSearchFlightRequest.setTravelClass(travelClass.getValue());

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
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departureDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0);
            }
        });

        SpinnerValueFactory<Integer> adultsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        adults.setValueFactory(adultsFactory);
        adults.setEditable(false);

        if (userLoginObserver.getUser() != null) {
            loggedname.setText(userLoginObserver.getUser().getEmail());
        } else {
            lias.setText("Not logged in");
            search_SB1.setVisible(false);
            loginError1.setText("Register or LogIn if you want to buy a ticket.");
        }


        ObservableList<String> listCity = FXCollections.observableArrayList(AirportCode
                .getByIata()
                .values()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList())
        );

        originLocationCode.setItems(listCity);
        destinationLocationCode.setItems(listCity);
        travelClass.setItems(FXCollections.observableArrayList(List.of("ECONOMY", "PREMIUM_ECONOMY", "BUSINESS", "FIRST")));

    }

    private void showFlights(List<FlightOfferSearch> tList) {
        ObservableList<FlightOfferSearch> list = FXCollections.observableArrayList(tList);

        col1.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId()));

        col2.setCellValueFactory(param -> new ReadOnlyStringWrapper(originValue + " (" + Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getIataCode() + ")"));

        col3.setCellValueFactory(param -> new ReadOnlyStringWrapper(destValue + " (" + Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getIataCode() + ")"));

        col4.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getDeparture().getAt()).substring(11)));

        col5.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(Arrays.stream(Arrays.stream(param.getValue().getItineraries()).findFirst().get().getSegments()).findFirst().get().getArrival().getAt()).substring(11)));

        col6.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPrice().getTotal() + " €"));

        col7.setCellValueFactory(param -> new ReadOnlyStringWrapper(Arrays.stream(Arrays.stream(param.getValue().getTravelerPricings()).findFirst().get().getFareDetailsBySegment()).findFirst().get().getCabin()));

        tableView.setItems(list);
    }


    @Override
    public void update(User user) {
        this.user = user;
    }

    public void bookTicket() {
        ;
        ReservationFlightRequest reservationFlightRequest = new ReservationFlightRequest(new Gson().toJson(tableView.getSelectionModel().getSelectedItem()));
    }
}
