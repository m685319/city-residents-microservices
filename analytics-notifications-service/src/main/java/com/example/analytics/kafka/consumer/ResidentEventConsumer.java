package com.example.analytics.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResidentEventConsumer {

    @KafkaListener(topics = "resident-events", groupId = "analytics-service")
    public void consume(String message) {
        log.info("Received resident event: {}", message);
    }
}
