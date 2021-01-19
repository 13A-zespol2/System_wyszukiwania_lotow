package com.strategy;

import com.amadeus.resources.Traveler;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomTravelerCreationStrategy implements TravelerCreationStrategy {
    private int countryCallingCode;
    private int phoneNumber;
    private String deviceType;
    private String documentType;
    private String numberDocument;
    private String expireDate;
    private String issuanceCountry ;
    private String nationality;
    private int idMyTraveler;
    private String name;
    private String surname;
    private String dateOfBirth;

    public CustomTravelerCreationStrategy(int countryCallingCode, int phoneNumber, String deviceType, String documentType, String numberDocument, String expireDate, String issuanceCountry, String nationality, int idMyTraveler, String name, String surname, String dateOfBirth) {
        this.countryCallingCode = countryCallingCode;
        this.phoneNumber = phoneNumber;
        this.deviceType = deviceType;
        this.documentType = documentType;
        this.numberDocument = numberDocument;
        this.expireDate = expireDate;
        this.issuanceCountry = issuanceCountry;
        this.nationality = nationality;
        this.idMyTraveler = idMyTraveler;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Traveler createTraveler() {

        Traveler traveler = new Traveler();

        traveler.setId(String.valueOf(idMyTraveler));
        traveler.setDateOfBirth(dateOfBirth);
        traveler.setName(traveler.new Name(name, surname));

        Traveler.Phone[] phone = new Traveler.Phone[1];
        phone[0] = traveler.new Phone();
        phone[0].setNumber(String.valueOf(phoneNumber));
        phone[0].setDeviceType(deviceType);
        phone[0].setCountryCallingCode(String.valueOf(countryCallingCode));
        Traveler.Contact contact = traveler.new Contact();
        contact.setPhones(phone);
        traveler.setContact(contact);

        Traveler.Document[] documents = new Traveler.Document[1];
        documents[0] = traveler.new Document();
        documents[0].setDocumentType(documentType);
        documents[0].setNumber(String.valueOf(numberDocument));
        documents[0].setExpiryDate(expireDate);
        documents[0].setIssuanceCountry(issuanceCountry);
        documents[0].setNationality(nationality);
        documents[0].setHolder(true);
        traveler.setDocuments(documents);


        return traveler;
    }
}
