package com.repository.model.builder.user;

public interface BuilderUser {

    void setIdTraveler(int idTraveler);

    void setName(String name);

    void setSurname(String surname);

    void setDateOfBirth(String dateOfBirth);

    void setNumber(String phoneNumber);

    void setCountryCallCode(String countryCallCode);

    void setDocumentType(String documentType);

    void setDocumentNumber(String documentNumber);

    void setIssuanceCountry(String issuanceCountry);

    void setNationality(String nationality);

    void setHolder(boolean holder);


}
