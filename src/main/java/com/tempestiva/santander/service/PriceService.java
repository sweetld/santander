package com.tempestiva.santander.service;

import com.tempestiva.santander.db.PriceRepository;
import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.model.Commission;
import com.tempestiva.santander.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Service
@Slf4j
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceService implements InitializingBean {
    private final PriceRepository priceRepository;
    private final Publisher publisher;
    private final BlockingDeque<Price> priceQueue = new LinkedBlockingDeque<>(100);
    private boolean running = false;

    public Boolean offer(final Price price) {
        return priceQueue.offer(price);
    }

    public Price getPriceFor(CCYPair ccyPair) {
        return priceRepository.getPriceFor(ccyPair);
    }

    public void processQueue() {
        try {
            while (running) {
                Price price = priceQueue.take();
                Price adjustedPrice = Commission.adjust.apply(price);
                log.info("Commission Applied Bid {} Ask {}", adjustedPrice.getBid(), adjustedPrice.getAsk());
                priceRepository.save(CCYPair.from(adjustedPrice.getInstrumentName()), adjustedPrice);
                publisher.publish(adjustedPrice);
            }
        } catch (InterruptedException e) {
            log.error("Failed to process Price queue.", e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void afterPropertiesSet() {
        running = true;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(this::processQueue);
    }
}
