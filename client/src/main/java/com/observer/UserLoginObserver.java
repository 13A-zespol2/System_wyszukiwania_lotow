package com.observer;

import com.repository.model.database.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Component
public class UserLoginObserver {
    private final List<LoginObserver> loginObservers = new ArrayList<>();
    private User user;

    //TODO OBSERWER
    public void loginNotify(User user) {
        this.user = user;
        loginObservers.forEach(e -> e.update(this.user));
    }

    public User getUser() {
        return user;
    }

    public void addObserver(LoginObserver loginObserver) {
        this.loginObservers.add(loginObserver);
    }


    public void deleteObserver(LoginObserver loginObserver) {
        this.loginObservers.remove(loginObserver);
    }
}
