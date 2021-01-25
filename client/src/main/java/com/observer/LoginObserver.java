package com.observer;

import com.repository.model.database.User;

/**
 * Interfejs do wzorca projektowego ,,Observer".
 */
public interface LoginObserver {
    void update(User user);
}
