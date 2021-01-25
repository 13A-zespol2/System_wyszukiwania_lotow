package com.strategy;

import com.amadeus.resources.Traveler;

/**
 * Interfejs wykorzystywany do wzorca projektowego ,,Strategy".
 */
public interface TravelerCreationStrategy {
    Traveler createTraveler();

}
