package com.gui;


import com.repository.model.communication.ReservedFlightsRequest;
import com.repository.model.communication.ReservedFlightsResponse;
import com.repository.model.data.FlightOrderDTO;
import com.repository.model.database.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
@Component
public class ClientTickets extends GuiPanel {
    @FXML
    public TableView<FlightOrderDTO> tableView;
    @FXML
    public TableColumn<FlightOrderDTO, String> col1;
    @FXML
    public TableColumn<FlightOrderDTO, String> col2;
    @FXML
    public TableColumn<FlightOrderDTO, String> col3;
    @FXML
    public TableColumn<FlightOrderDTO, String> col4;
    @FXML
    public TableColumn<FlightOrderDTO, String> col5;
    @FXML
    public TableColumn<FlightOrderDTO, String> col6;
    @FXML
    public TableColumn<FlightOrderDTO, String> col7;

    @FXML
    private Label loggedname;


    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }


    @Override
    public void update(User user) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.getColumns().forEach(e -> e.setReorderable(false));
        ReservedFlightsRequest reservedFlightsRequest = new ReservedFlightsRequest(userLoginObserver.getUser());
        ReservedFlightsResponse reservedFlight = clientControl.getReservedFlight(reservedFlightsRequest);
        if (reservedFlight.getFlightOfferSearchDTOList() != null) {

            ObservableList<FlightOrderDTO> list = FXCollections.observableArrayList(reservedFlight.getFlightOfferSearchDTOList());

            col1.setCellValueFactory(param -> new ReadOnlyStringWrapper(String.valueOf(param.getValue().getQuantityOfTickets())));

            col2.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDepartureIATA()));

            col3.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDestinationIATA()));

            col4.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDepartureTime().replace("T", "  ")));

            col5.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getArrivalTime().replace("T", "  ")));

            col6.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTicketPrice() * param.getValue().getQuantityOfTickets() + " " + param.getValue().getCurrency()));

            col7.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getFlightClass()));

            tableView.setItems(list);

        }
        loggedname.setText(userLoginObserver.getUser().getEmail());
    }
}
