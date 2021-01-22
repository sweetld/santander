package com.tempestiva.santander.service;

import com.tempestiva.santander.db.InMemoryPriceRepository;
import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ConsumerTest {

    @Test
    public final void whenMessagePricePersisted() throws InterruptedException {
        InMemoryPriceRepository priceRepository = new InMemoryPriceRepository();
        Publisher publisher = new Publisher();
        PriceService priceService = new PriceService(priceRepository, publisher);
        Consumer consumer = new Consumer(priceService);
        consumer.onMessage("125,EUR/JPY, 6.9105,7.5382,21-01-2021 06:31:13:994");
        priceService.afterPropertiesSet();
        Thread.sleep(1000);
        priceService.setRunning(false);
        Assertions.assertEquals(Price.builder()
                                     .id(125L)
                                     .instrumentName("EUR/JPY")
                                     .bid(6.9035895d)
                                     .ask(7.5457382d)
                                     .timestamp(LocalDateTime.parse("2021-01-21T06:31:13.994"))
                                     .build(), priceRepository.getPriceFor(CCYPair.EUR_JPY));
    }

    @Test
    public final void whenTwoLineMessagePricesPersisted() throws InterruptedException {
        InMemoryPriceRepository priceRepository = new InMemoryPriceRepository();
        Publisher publisher = new Publisher();
        PriceService priceService = new PriceService(priceRepository, publisher);
        Consumer consumer = new Consumer(priceService);
        consumer.onMessage("125,EUR/JPY, 6.9105,7.5382,21-01-2021 06:31:13:994\n126,GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100\n");
        priceService.afterPropertiesSet();
        Thread.sleep(1000);
        priceService.setRunning(false);
        Assertions.assertEquals(Price.builder()
                                     .id(125L)
                                     .instrumentName("EUR/JPY")
                                     .bid(6.9035895d)
                                     .ask(7.5457382d)
                                     .timestamp(LocalDateTime.parse("2021-01-21T06:31:13.994"))
                                     .build(), priceRepository.getPriceFor(CCYPair.EUR_JPY));
    }

    @Test
    public final void whenEmptyMessageNoPricePersisted() throws InterruptedException {
        InMemoryPriceRepository priceRepository = new InMemoryPriceRepository();
        Publisher publisher = new Publisher();
        PriceService priceService = new PriceService(priceRepository, publisher);
        Consumer consumer = new Consumer(priceService);
        consumer.onMessage("");
        priceService.afterPropertiesSet();
        Thread.sleep(1000);
        priceService.setRunning(false);
        Assertions.assertNull(priceRepository.getPriceFor(CCYPair.EUR_JPY));
    }

}
