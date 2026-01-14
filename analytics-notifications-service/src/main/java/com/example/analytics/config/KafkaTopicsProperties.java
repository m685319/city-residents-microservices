package com.example.analytics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka.topics")
public class KafkaTopicsProperties {

    private String residentEvents;

    public String getResidentEvents() {
        return residentEvents;
    }

    public void setResidentEvents(String residentEvents) {
        this.residentEvents = residentEvents;
    }
}