package com.example.analytics.kafka.consumer;

import com.example.shared.event.ResidentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResidentEventConsumer {
    @KafkaListener(
            topics = "resident-events",
            groupId = "analytics-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(ResidentCreatedEvent event) {
        log.info(
                "Received resident event: id={}, passport={}, gender={}",
                event.getResidentId(),
                event.getPassportNumber(),
                event.getGender()
        );
    }
}
