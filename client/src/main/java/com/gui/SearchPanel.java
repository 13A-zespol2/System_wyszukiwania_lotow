package com.gui;

import com.repository.model.communication.ReservationFlightRequest;
import com.repository.model.communication.SearchFlightRequest;
import com.repository.model.communication.SearchFlightResponse;
import com.repository.model.data.AirportCode;
import com.repository.model.data.FlightOfferSearchDTO;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
@Controller
@Component
public class SearchPanel extends GuiPanel {

    @FXML
    public TableView<FlightOfferSearchDTO> tableView;
    private User user;
    @FXML
    private ComboBox<String> originLocationCode;
    @FXML
    private ComboBox<String> destinationLocationCode;
    @FXML
    private DatePicker departureDate;
    @FXML
    private ComboBox<String> travelClass;
    @FXML
    private Spinner<Integer> adults;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col1;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col2;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col3;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col4;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col5;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col6;
    @FXML
    private TableColumn<FlightOfferSearchDTO, String> col7;
    @FXML
    private Label loggedname;
    @FXML
    private Label lias;

    @FXML
    private Label loginError;
    @FXML
    private Label loginError1;
    @FXML
    private Button search_SB1;


    public void searchFlights() {

        if ((originLocationCode.getValue() == null) || (destinationLocationCode.getValue() == null) ||
                (departureDate.getValue() == null) || (adults.getValue() == null) ||
                (travelClass.getValue() == null)) {
            loginError.setText("Complete required fields!");
        } else {
            loginError.setText("");
            SearchFlightRequest createSearchFlightRequest = new SearchFlightRequest();
            createSearchFlightRequest.setOriginLocationCode(AirportCode.getKeyByValue(originLocationCode.getValue()));
            createSearchFlightRequest.setDestinationLocationCode(AirportCode.getKeyByValue(destinationLocationCode.getValue()));
            createSearchFlightRequest.setDepartureDate(String.valueOf(departureDate.getValue()));
            createSearchFlightRequest.setAdults(String.valueOf(adults.getValue()));
            createSearchFlightRequest.setTravelClass(travelClass.getValue());

            SearchFlightResponse searchFlightResponse = clientControl.searchFlight(createSearchFlightRequest);

            if (searchFlightResponse.getTList() == null)
                loginError.setText("No flights found.");

            showFlights(searchFlightResponse.getTList());

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


        adults.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
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

    private void showFlights(List<FlightOfferSearchDTO> tList) {
        ObservableList<FlightOfferSearchDTO> list = FXCollections.observableArrayList(tList);

        col1.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId()));

        col2.setCellValueFactory(param -> new ReadOnlyStringWrapper(AirportCode.getByIata().get(param.getValue().getDepartureIATA()) + " (" + param.getValue().getDepartureIATA() + ")"));

        col3.setCellValueFactory(param -> new ReadOnlyStringWrapper(AirportCode.getByIata().get(param.getValue().getDestinationIATA()) + " (" + param.getValue().getDestinationIATA() + ")"));

        col4.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDepartureTime().substring(11)));

        col5.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getArrivalTime().substring(11)));

        col6.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTicketPrice() + param.getValue().getCurrency()));

        col7.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getFlightClass()));

        tableView.setItems(list);
    }


    @Override
    public void update(User user) {
        this.user = user;
    }

    public void bookTicket() {

        ReservationFlightRequest reservationFlightRequest = new ReservationFlightRequest(tableView.getSelectionModel().getSelectedItem(), userLoginObserver.getUser(), adults.getValue());
        clientControl.flightReservation(reservationFlightRequest);

    }
}
