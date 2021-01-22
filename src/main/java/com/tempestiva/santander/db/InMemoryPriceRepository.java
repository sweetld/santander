package com.tempestiva.santander.db;

import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.model.Price;
import org.springframework.stereotype.Repository;

import java.util.EnumMap;

@Repository
public class InMemoryPriceRepository implements PriceRepository {
    private final EnumMap<CCYPair, Price> fxPrices = new EnumMap<>(CCYPair.class);

    @Override
    public Price getPriceFor(final CCYPair ccyPair) {
        if (ccyPair == null) {
            return null;
        }
        return fxPrices.get(ccyPair);
    }

    @Override
    public void save(final CCYPair ccyPair, final Price price) {
        fxPrices.put(ccyPair, price);
    }
}
