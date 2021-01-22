package com.tempestiva.santander.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CCYPairTest {

    @Test
    public void whenEmptyThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CCYPair.from(""));
    }

    @Test
    public void whenEUR_JPY() {
        CCYPair test = CCYPair.from("EUR/JPY");
        Assertions.assertEquals(CCYPair.EUR_JPY, test);
    }

}
