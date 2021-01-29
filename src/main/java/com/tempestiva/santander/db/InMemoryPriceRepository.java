package com.tempestiva.santander.db;

import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.model.Price;
import org.springframework.stereotype.Repository;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPriceRepository implements PriceRepository {
    private final Map<CCYPair, Price> fxPrices = new ConcurrentHashMap<>();

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
