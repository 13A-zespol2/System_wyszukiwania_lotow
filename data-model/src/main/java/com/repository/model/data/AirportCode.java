package com.repository.model.data;

import java.util.HashMap;
import java.util.Map;

public enum AirportCode {
    WARSAW("WAW");
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
