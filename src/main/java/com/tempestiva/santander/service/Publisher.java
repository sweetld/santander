package com.tempestiva.santander.service;

import com.tempestiva.santander.model.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Publisher {

    public void publish(Price price) {
        log.info("Publishing Price to REST endpoint {}", price);
    }
}
