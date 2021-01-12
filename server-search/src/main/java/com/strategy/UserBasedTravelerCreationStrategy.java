package com.strategy;

import com.amadeus.resources.Traveler;
import com.repository.model.database.User;

public class UserBasedTravelerCreationStrategy implements TravelerCreationStrategy {
    private final User user;

    public UserBasedTravelerCreationStrategy(User user) {
        this.user = user;
    }

    @Override
    public Traveler createTraveler() {


        return null;
    }
}
