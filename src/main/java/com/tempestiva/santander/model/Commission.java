package com.tempestiva.santander.model;

import java.util.Optional;
import java.util.function.Function;

public class Commission {

    private Commission() throws IllegalAccessException {
        throw new IllegalAccessException("Utility Class");
    }

    public static final Function<Price, Price> adjust = price -> {
        Double bidAdjusted = Optional.ofNullable(price.getBid())
                                     .map(bid -> bid - (bid * 0.1 / 100))
                                     .orElse(0d);
        Double askAdjusted = Optional.ofNullable(price.getAsk())
                                     .map(ask -> ask + (ask * 0.1 / 100))
                                     .orElse(0d);
        return price.toBuilder()
                    .bid(bidAdjusted)
                    .ask(askAdjusted)
                    .build();
    };

}
