package com.observer;

import com.repository.model.database.User;

public interface LoginObserver {
    void update(User user);
}
