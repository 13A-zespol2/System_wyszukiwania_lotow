package com.repository.model.database;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Reservation implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String reservationApiCode;
    @ManyToOne
    @JoinColumn(name = "myTraveler", referencedColumnName = "idMyTraveler")
    private MyTraveler myTraveler;


    public Reservation(int id, String reservationApiCode, MyTraveler myTraveler) {
        this.id = id;
        this.reservationApiCode = reservationApiCode;
        this.myTraveler = myTraveler;
    }


}
