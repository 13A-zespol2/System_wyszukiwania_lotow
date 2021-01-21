package com.amadeus;

import com.ServerException;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Traveler;
import com.repository.model.communication.CreateFlightReservationRequest;
import com.repository.model.communication.SearchFlightRequest;

import java.util.List;
import java.util.Optional;

public class AmadeusFacade {


    private final AmadeusFlightSearch amadeusFlightSearch;
    private final AmadeusOrderManagement amadeusOrderManagement;
    private final AmadeusCreateOrder amadeusCreateOrder;

    public AmadeusFacade() {
        Amadeus amadeus = Amadeus
                .builder("8LEPfmoU2BlW8PtxGwE2iu8HrjwAERYQ", "M5oxj0KGEobsAgGQ")
                .build();

        this.amadeusFlightSearch = new AmadeusFlightSearch(amadeus);
        this.amadeusOrderManagement = new AmadeusOrderManagement(amadeus);
        this.amadeusCreateOrder = new AmadeusCreateOrder(amadeus);
    }


    public Optional<String> createOrderFlight(CreateFlightReservationRequest createFlightReservation) {
        String flightOrder;
        try {

            flightOrder = amadeusCreateOrder.createFlightOrder((Traveler[]) createFlightReservation.getTravelers().toArray(), createFlightReservation.getFlightOfferSearch());
        } catch (ResponseException e) {
            throw new ServerException("Api error create order flight: " + e);
        }

        return flightOrder == null ? Optional.empty() : Optional.of(flightOrder);
    }
    //TODO !!!!
    public Optional<Object> getOrderedFlight(String idOrder) {
        return Optional.empty();
    }


    public List<FlightOfferSearch> searchFlight(SearchFlightRequest searchFlightRequest) {
        FlightOfferSearch[] flightOfferSearches;
        amadeusFlightSearch.setOriginLocationCode(searchFlightRequest.getOriginLocationCode());
        amadeusFlightSearch.setDestinationLocationCode(searchFlightRequest.getDestinationLocationCode());
        amadeusFlightSearch.setDepartureDate(searchFlightRequest.getDepartureDate());
//        if (searchFlightRequest.getReturnDate().isEmpty())
//            amadeusFlightSearch.setReturnDate(searchFlightRequest.getReturnDate());
        amadeusFlightSearch.setAdults(String.valueOf(searchFlightRequest.getAdults()));
        amadeusFlightSearch.setTravelClass(searchFlightRequest.getTravelClass());
        /*amadeusFlightSearch.setChildren(String.valueOf(searchFlightRequest.getChildren()));*/
        try {
            flightOfferSearches = amadeusFlightSearch.searchFlight();

        } catch (ResponseException e) {
            return null;
        }
        return List.of(flightOfferSearches);
    }
}
