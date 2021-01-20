package com.observer;

import com.repository.model.database.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class UserLoginObserver {
    private User user;

        //TODO OBSERWER
    public User loginNotify(User user) {
        this.user = user;
        return this.user;
    }

    public User loginNotify() {
        return this.user;
    }

}
