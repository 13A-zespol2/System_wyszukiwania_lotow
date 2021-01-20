package com.repository.model.data;

import java.util.HashMap;
import java.util.Map;

public enum CountryCode {
    POLAND("PL"),
    LISBON("LIS"),
    AUSTRIA("AT"),
    BELGIUM("BE"),
    GREECE("GR"),
    GERMANY("DE"),
    USA("US"),
    UKRAINE("UK"),
    SPAIN("ES"),
    NORWAY("NO");

    private static final Map<String, CountryCode> codeHashMap = new HashMap<>();

    static {
        for (CountryCode e : values()) {
            codeHashMap.put(e.codeCountry, e);
        }
    }

    public final String codeCountry;


    CountryCode(String codeCountry) {
        this.codeCountry = codeCountry;
    }
}
