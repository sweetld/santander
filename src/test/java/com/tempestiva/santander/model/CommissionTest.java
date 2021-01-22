package com.tempestiva.santander.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommissionTest {

    @Test
    public void whenBidIsTenCommissionIsNine() {
        Price price = Price.builder()
                           .bid(10d)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(9.99d, adjustedPrice.getBid());
    }

    @Test
    public void whenAskIsTenCommissionIsTen() {
        Price price = Price.builder()
                           .ask(10d)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(10.01d, adjustedPrice.getAsk());
    }

    @Test
    public void whenBidIsNegativeCommissionIsNegative() {
        Price price = Price.builder()
                           .bid(-10d)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(-9.99d, adjustedPrice.getBid());
    }

    @Test
    public void whenAskIsNegativeCommissionIsNegative() {
        Price price = Price.builder()
                           .ask(-10d)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(-10.01d, adjustedPrice.getAsk());
    }

    @Test
    public void whenBidIsZeroCommissionIsZero() {
        Price price = Price.builder()
                           .bid(0d)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(0d, adjustedPrice.getBid());
    }

    @Test
    public void whenAskIsZeroCommissionIsZero() {
        Price price = Price.builder()
                           .ask(0d)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(0d, adjustedPrice.getAsk());
    }

    @Test
    public void whenBidIsNullCommissionIsZero() {
        Price price = Price.builder()
                           .bid(null)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(0d, adjustedPrice.getBid());
    }

    @Test
    public void whenAskIsNullCommissionIsZero() {
        Price price = Price.builder()
                           .ask(null)
                           .build();
        Price adjustedPrice = Commission.adjust.apply(price);
        Assertions.assertEquals(0d, adjustedPrice.getAsk());
    }

}
