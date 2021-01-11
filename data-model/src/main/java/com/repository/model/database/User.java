package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String dateOfBirth;
    private Integer phoneId;
    private Integer documentId;
    private Integer creditCardId;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    public User(Integer id, String name, String email, String dateOfBirth, Integer phoneId, Integer documentId, Integer creditCardId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneId = phoneId;
        this.documentId = documentId;
        this.creditCardId = creditCardId;

    }

    public User() {

    }


}