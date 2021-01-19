package com.observer;

import com.repository.model.database.User;

public interface LoginListener {
    void update(User user);
}
