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
    @SequenceGenerator(name = "travelerMyGenerator", sequenceName = "travSeq", initialValue = 1)
    @GeneratedValue(generator = "travelerMyGenerator")
    private int id;
    private String name;
    private String surname;
    private String dateOfBirth;
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "travelerPhone", referencedColumnName = "id")
    private TravelerPhone travelerPhone;


    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String dateOfBirth;


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname=surname;
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth){
            this.dateOfBirth=dateOfBirth;
            return this;
        }

        public MyTraveler build(){
            return new MyTraveler(this);
        }
    }

    private MyTraveler(Builder b){
        this.name = b.name;
        this.surname = b.surname;
        this.dateOfBirth = b.dateOfBirth;
    }

    public MyTraveler() {
    }

}
