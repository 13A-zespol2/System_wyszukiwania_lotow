package com.client;


import com.amadeus.Amadeus;
import com.amadeus.AmadeusFacade;
import com.amadeus.resources.Traveler;
import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.strategy.CustomTravelerCreationStrategy;
import com.strategy.UserBasedTravelerCreationStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.Map;

class DemoApplicationTests {



    @Test
    public void strategyCustomTest(){
        CustomTravelerCreationStrategy customTravelerCreationStrategy = CustomTravelerCreationStrategy.builder()
                .idMyTraveler(1)
                .countryCallingCode(48)
                .phoneNumber(123345567)
                .deviceType("MOBILE")
                .documentType("PASSPORT")
                .numberDocument("CYA123123")
                .expireDate("2030-11-11")
                .issuanceCountry("PL")
                .nationality("PL")
                .name("Karol")
                .surname("Grelewicz")
                .dateOfBirth("1998-11-24")
                .build();


        assertTrue(customTravelerCreationStrategy.createTraveler() instanceof Traveler);

    }
    @Test
    public void strategyUserBasedTest(){
        MyTraveler myTraveler = new MyTraveler.Builder()
                .name("Karol")
                .surname("Mik")
                .dateOfBirth("1998-24-11")
                .build();

        TravelerDocument travelerDocument = new TravelerDocument.Builder()
                .documentType("PASSPORT")
                .expireDate("2030-11-11")
                .numberDocument("CYA123123")
                .nationality("PL")
                .issuanceCountry("PL")
                .build();

        TravelerPhone travelerPhone = new TravelerPhone.Builder()
                .deviceType("MOBILE")
                .phoneNumber(123123123)
                .countryCallingCode(48)
                .build();

        UserBasedTravelerCreationStrategy userBasedTravelerCreationStrategy = new UserBasedTravelerCreationStrategy(myTraveler, travelerDocument, travelerPhone);

        assertTrue(userBasedTravelerCreationStrategy.createTraveler() instanceof Traveler);

    }

}
