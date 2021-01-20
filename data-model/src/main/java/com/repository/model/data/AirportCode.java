package com.repository.model.data;

import java.util.HashMap;
import java.util.Map;

public enum AirportCode {
    WARSAW("WAW"),
    LISBON("LIS"),
    ZURICH("ZRH"),
    BERLIN("BER"),
    CRACOW("KRK"),
    LONDON("LON"),
    RADOM("RDO"),
    GDANSK("GDN"),
    NEW_YORK("NYC"),
    PARIS("PAR"),
    MADRID("MAD"),
    MOSCOW("MOW"),
    LODZ("LCJ");


    private static final Map<String, AirportCode> BY_IATA = new HashMap<>();

    static {
        for (AirportCode e : values()) {
            BY_IATA.put(e.IATACode, e);
        }
    }

    public final String IATACode;


    AirportCode(String IATACode) {
        this.IATACode = IATACode;
    }

    public static Map<String, AirportCode> getByIata() {
        return BY_IATA;
    }
}
