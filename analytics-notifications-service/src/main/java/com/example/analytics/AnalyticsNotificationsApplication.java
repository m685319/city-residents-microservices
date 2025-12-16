package com.example.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AnalyticsNotificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsNotificationsApplication.class, args);
    }
}