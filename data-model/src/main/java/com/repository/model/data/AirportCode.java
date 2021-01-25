package com.repository.model.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum AirportCode {
    WARSAW("WAW"),
    LISBON("LIS"),
    ZURICH("ZRH"),
    BERLIN("BER"),
    CRACOW("KRK"),
    LONDON("LGW"),
    GDANSK("GDN"),
    NEW_YORK("NYC"),
    PARIS("PAR"),
    MADRID("MAD"),
    MOSCOW("MOW"),
    VENICE("VCE"),
    ROMA("FCO"),
    OSLO("OSL"),
    HAMBURG("HAM"),
    BUDAPEST("BUD"),
    BARCELONA("BCN");


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


    public static <T, E> String getKeyByValue(String value) {
        for (Map.Entry<String, AirportCode> entry : BY_IATA.entrySet()) {
            if (Objects.equals(value, entry.getValue().name())) {
                return entry.getKey();
            }
        }
        return null;
    }


}
