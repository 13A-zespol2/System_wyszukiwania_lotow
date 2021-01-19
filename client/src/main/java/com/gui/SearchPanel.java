package com.gui;

import com.amadeus.resources.FlightOfferSearch;
import com.client.ClientControl;
import com.gluonhq.charm.glisten.control.Icon;
import com.repository.model.communication.SearchFlightRequest;
import com.repository.model.communication.SearchFlightResponse;
import com.repository.model.data.AirportCode;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Slf4j
@Controller
@Component
public class SearchPanel implements FxmlLoader, Initializable {
    @Autowired
    private ClientControl clientControl;
    @Autowired
    private MainPanel mainPanel;
    @Autowired
    private SpringFxmlLoader springFxmlLoader;


    @FXML
    private Icon icon;

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
    private Button search_SB;
    @FXML
    private TableColumn<FlightOfferSearch, String> col1;

    @FXML
    private TableColumn<?, ?> col2;

    @FXML
    private TableColumn<?, ?> col3;

    @FXML
    private TableColumn<?, ?> col4;

    @FXML
    private TableColumn<?, ?> col5;

    @FXML
    private TableColumn<?, ?> col6;

    @FXML
    private TableColumn<?, ?> col7;


    public void homeFunc(MouseEvent event) {
        mainPanel.getMainLoad().getChildren().clear();
        mainPanel.getMainLoad().getChildren().add(loadUi("/MainPanel"));
    }

    public void exit_btn(MouseEvent event) {
        System.exit(0);
    }

    public void minimize_btn(MouseEvent event) {
        TopBar topbar = new TopBar();
        topbar.minimize_btn(event);
    }

    @Override
    public AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    public void searchFlights() {
        SearchFlightRequest createSearchFlightRequest = new SearchFlightRequest();
        createSearchFlightRequest.setDestinationLocationCode(destinationLocationCode.getValue());
        createSearchFlightRequest.setOriginLocationCode(originLocationCode.getValue());
        createSearchFlightRequest.setReturnDate(String.valueOf(departureDate.getValue()));
        if (returnCheckbox.isSelected())
            createSearchFlightRequest.setReturnDate(String.valueOf(returnDate.getValue()));
        if (isNumeric(children.getText()))
            createSearchFlightRequest.setChildren(children.getText());
        if (isNumeric(adults.getText()))
            createSearchFlightRequest.setAdults(adults.getText());
        createSearchFlightRequest.setTravelClass(travelClass.getValue());

        SearchFlightRequest createUserRequest = new SearchFlightRequest("PAR", "NYC", "2021-01-21", "1", "ECONOMY", "0", false, "");


        SearchFlightResponse searchFlightResponse = clientControl.searchFlight(createUserRequest);

        if (searchFlightResponse != null) {
            List<FlightOfferSearch> tList = searchFlightResponse.getTList();
            showFlights(tList);
        }

        System.out.println("Sad");
        // if(searchFlightResponse != null)


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listCity = FXCollections.observableArrayList();
        for (Map.Entry<String, AirportCode> entry : AirportCode.getByIata().entrySet()) {
            listCity.add(entry.getKey());
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
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void showFlights(List<FlightOfferSearch> tList) {
        ObservableList<FlightOfferSearch> list = FXCollections.observableArrayList();
        list.addAll(tList);

        col1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FlightOfferSearch, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FlightOfferSearch, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getId());
            }
        });
        System.out.println("dsa");




    }



}
