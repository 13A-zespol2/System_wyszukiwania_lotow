package com.strategy;

import com.amadeus.resources.Traveler;
import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserBasedTravelerCreationStrategy implements TravelerCreationStrategy {
    private final MyTraveler myTraveler;
    private final TravelerDocument travelerDocument;
    private final TravelerPhone travelerPhone;

    public UserBasedTravelerCreationStrategy(MyTraveler myTraveler, TravelerDocument travelerDocument, TravelerPhone travelerPhone) {
        this.myTraveler = myTraveler;
        this.travelerDocument = travelerDocument;
        this.travelerPhone = travelerPhone;
    }


    @Override
    public Traveler createTraveler() {
        Traveler traveler = new Traveler();

        traveler.setId(String.valueOf(1));
        traveler.setDateOfBirth(myTraveler.getDateOfBirth());
        traveler.setName(traveler.new Name(myTraveler.getName(), myTraveler.getSurname()));

        Traveler.Phone[] phone = new Traveler.Phone[1];
        phone[0] = traveler.new Phone();
        phone[0].setNumber(String.valueOf(travelerPhone.getPhoneNumber()));
        phone[0].setDeviceType(travelerPhone.getDeviceType());
        phone[0].setCountryCallingCode(String.valueOf(travelerPhone.getCountryCallingCode()));
        Traveler.Contact contact = traveler.new Contact();
        contact.setPhones(phone);
        traveler.setContact(contact);

        Traveler.Document[] documents = new Traveler.Document[1];
        documents[0] = traveler.new Document();
        documents[0].setDocumentType(travelerDocument.getDocumentType());
        documents[0].setNumber(String.valueOf(travelerDocument.getNumberDocument()));
        documents[0].setExpiryDate(travelerDocument.getExpireDate());
        documents[0].setIssuanceCountry(travelerDocument.getIssuanceCountry());
        documents[0].setNationality(travelerDocument.getNationality());
        documents[0].setHolder(true);
        traveler.setDocuments(documents);


        return traveler;
    }
}
