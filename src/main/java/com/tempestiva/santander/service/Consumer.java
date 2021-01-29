package com.tempestiva.santander.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tempestiva.santander.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Consumer {
    private final PriceService priceService;

    public void onMessage(String message) {
        log.info("Received Message: {}", message);
        try (StringReader reader = new StringReader(message)) {
            List<Price> prices = new CsvToBeanBuilder<Price>(reader)
                .withOrderedResults(true)
                .withType(Price.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build()
                .parse();
            prices.forEach(priceService::offer);
        }
    }
}
