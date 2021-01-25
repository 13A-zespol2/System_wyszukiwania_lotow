package com.repository.model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
/**
 * Klasa odpowiadająca za tabelę ,,User". Zastosowany jest w niej wzorzec projektowy ,,Builder".
 * Deklaracja ,,@Entity" konwertuje tą klasę na klasę bazodanową i tworzy połączenie z innymi tabelami (klasami).
 */
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "userGenerator", sequenceName = "myUserSeq", initialValue = 1)
    @GeneratedValue(generator = "userGenerator")
    private int id;
    @Column(unique = true)
    private String email;
    private String password;


    public static class Builder {
        private String email;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    private User(Builder b){
        this.email = b.email;
        this.password = b.password;
    }

    public User() {

    }
}