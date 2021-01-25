package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.Traveler;
import com.repository.model.database.User;

import java.util.List;
import java.util.Optional;

public class AmadeusFacade {


    private final AmadeusFlightSearch amadeusFlightSearch;
    private final AmadeusOrderManagement amadeusOrderManagement;
    private final CreateOrder amadeusCreateOrder;

    public AmadeusFacade() {
        Amadeus amadeus = Amadeus
                .builder("8LEPfmoU2BlW8PtxGwE2iu8HrjwAERYQ", "M5oxj0KGEobsAgGQ")
                .build();

        this.amadeusFlightSearch = new AmadeusFlightSearch(amadeus);
        this.amadeusOrderManagement = new AmadeusOrderManagement(amadeus);
        this.amadeusCreateOrder = new CreateOrderWithEmailNotification(new AmadeusCreateOrder(amadeus));
    }


    public Optional<String> createOrderFlight(Traveler[] travelers, FlightOfferSearch flightOfferSearch, User user, boolean phone) {

        try {
            return Optional.of(amadeusCreateOrder.createFlightOrder(travelers, flightOfferSearch, phone, user));

        } catch (ResponseException e) {
            return Optional.empty();
        }

    }

    public Optional<FlightOrder> getOrderedFlight(String idOrder) {

        try {
            return Optional.of(amadeusOrderManagement.getOrder(idOrder));
        } catch (ResponseException e) {
            return Optional.empty();
        }


    }


    public Optional<List<FlightOfferSearch>> searchFlight(String originLocationCode, String destinationLocationCode, String departureDate, String travelerClass) {
        amadeusFlightSearch.setOriginLocationCode(originLocationCode);
        amadeusFlightSearch.setDestinationLocationCode(destinationLocationCode);
        amadeusFlightSearch.setDepartureDate(departureDate);
        amadeusFlightSearch.setTravelClass(travelerClass);

        try {
            return Optional.of(List.of(amadeusFlightSearch.searchFlight()));

        } catch (ResponseException e) {
            return Optional.empty();
        }

    }
}
