package com.tempestiva.santander;

import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.service.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DummyProducer {
    private final Consumer consumer;
    private static long counter = 100;

    @Scheduled(fixedRate = 3000, initialDelay = 3000)
    public void sendPrice() {
        int ccyPairs = ThreadLocalRandom.current().nextInt(1, 3);
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss:SSS");
        for (int loop = 1; loop <= ccyPairs; loop++) {
            counter++;
            double midPrice = ThreadLocalRandom.current().nextDouble(1, 200);
            double spread = ThreadLocalRandom.current().nextDouble(0.002, 1);
            sb.append(String.format("%d, %s, %(.4f,%(.4f,%s",
                                    counter,
                                    CCYPair.getRandomCCYPair().name().replace("_", "/"),
                                    midPrice - (spread / 2),
                                    midPrice + (spread / 2),
                                    LocalDateTime.now().format(dateTimeFormatter)));
            sb.append("\n");
        }
        consumer.onMessage(sb.toString());
    }

}
