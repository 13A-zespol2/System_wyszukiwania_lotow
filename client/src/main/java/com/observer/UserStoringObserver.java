package com.observer;

import com.repository.model.database.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component

/**
 * Klasa przechowująca obiekt klasy User. Wykorzystywana we wzorcu projektowym ,,Observer".
 */
public class UserStoringObserver implements LoginObserver {
    private User user;


    public User getUser() {
        return user;
    }

    @Override
    public void update(User user) {
        this.user = user;
    }
}
