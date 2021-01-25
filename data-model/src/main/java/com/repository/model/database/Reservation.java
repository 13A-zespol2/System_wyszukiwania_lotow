package com.repository.model.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
/**
 * Klasa odpowiadająca za tabelę ,,Rezerwacje".
 * Deklaracja ,,@Entity" konwertuje tą klasę na klasę bazodanową i tworzy połączenie z innymi tabelami (klasami).
 */
public class Reservation implements Serializable {
    @Id
    @SequenceGenerator(name = "travelerReservGenerator", sequenceName = "resSeq", initialValue = 1)
    @GeneratedValue(generator = "travelerReservGenerator")
    private int id;
    private String reservationApiCode;
    @ManyToOne
    @JoinColumn(name = "myTraveler", referencedColumnName = "id")
    private MyTraveler myTraveler;
    private int quantityOfTickets;

    public Reservation(String reservationApiCode, MyTraveler myTraveler, int quantityOfTickets) {
        this.reservationApiCode = reservationApiCode;
        this.myTraveler = myTraveler;
        this.quantityOfTickets = quantityOfTickets;
    }

    public Reservation() {
    }
}
