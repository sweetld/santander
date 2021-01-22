package com.tempestiva.santander.controller;

import com.tempestiva.santander.model.CCYPair;
import com.tempestiva.santander.model.Price;
import com.tempestiva.santander.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceController {
    private final PriceService priceService;

    @GetMapping("/price/{instrumentName}")
    public Price getPriceFor(@PathVariable String instrumentName) {
        return priceService.getPriceFor(CCYPair.from(instrumentName));
    }
}
