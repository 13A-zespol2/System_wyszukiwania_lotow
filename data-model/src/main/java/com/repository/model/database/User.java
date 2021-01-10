package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String email1;


    public User(Integer id, String name, String email, String email1) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.email1 = email1;

    }

    public User() {

    }


}