package com.tempestiva.santander.db;

import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.model.Price;

public interface PriceRepository {
    Price getPriceFor(CCYPair ccyPair);

    void save(CCYPair ccyPair, Price price);
}
