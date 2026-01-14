package com.example.analytics;

import com.example.analytics.config.KafkaTopicsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KafkaTopicsProperties.class)
public class AnalyticsNotificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsNotificationsApplication.class, args);
    }
}