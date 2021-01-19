package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class MyTraveler implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMyTraveler;
    private String name;
    private String surname;
    private String dateOfBirth;
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "travelerPhone", referencedColumnName = "id")
    private TravelerPhone travelerPhone;

    public MyTraveler(int idMyTraveler, String name, String surname, String dateOfBirth) {
        this.idMyTraveler = idMyTraveler;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public MyTraveler(int idMyTraveler, String name, String surname, User user) {
        this.idMyTraveler = idMyTraveler;
        this.name = name;
        this.surname = surname;
        this.user = user;
    }

    public MyTraveler() {
    }
}
