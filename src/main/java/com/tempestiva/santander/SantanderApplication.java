package com.tempestiva.santander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SantanderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SantanderApplication.class, args);
    }
}
