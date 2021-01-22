package com.tempestiva.santander.model;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public enum CCYPair {
    EUR_USD,
    EUR_JPY,
    GBP_USD;

    public static CCYPair getRandomCCYPair() {
        return values()[ThreadLocalRandom.current().nextInt(0, values().length)];
    }

    public static CCYPair from(final String instrumentName) {
        if (instrumentName == null) {
            throw new IllegalArgumentException("Instrument Name cannot be null");
        }
        String searchTerm = instrumentName.trim().replace('/', '_');
        return Arrays.stream(values())
                     .filter(ccyPair -> ccyPair.name().equalsIgnoreCase(searchTerm))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(instrumentName + " invalid."));
    }
}
