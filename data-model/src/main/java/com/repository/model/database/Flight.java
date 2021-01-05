package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int flightId;
    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private boolean oneWay;
    private double adultPrice;
    private double childPrice;
    private double infantsPrice;
    private String classFlight;
    private String currencyCode;


    public Flight(String originLocationCode, String destinationLocationCode, String departureDate, double adultPrice) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
        this.departureDate = departureDate;
        this.adultPrice = adultPrice;
    }


}
