package com.repository.model.database;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String reservationApiCode;
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;


}
