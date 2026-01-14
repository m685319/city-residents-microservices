package com.example.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AnalyticsNotificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsNotificationsApplication.class, args);
    }
}