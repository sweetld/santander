package com.tempestiva.santander;

import com.tempestiva.santander.controller.PriceController;
import com.tempestiva.santander.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DummyConsumer {
    private final PriceController priceController;

    @Scheduled(fixedRate = 1, initialDelay = 3000)
    public void readPrice() {
        ThreadLocalRandom.current().nextDouble(1, 200);
        Price price = priceController.getPriceFor("EUR/USD");
        log.info("Received Price {}", price);
    }
}
