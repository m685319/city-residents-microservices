package com.example.cityresidents.kafka.producer;

import com.example.cityresidents.kafka.event.ResidentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResidentEventProducer {

    private static final String TOPIC = "resident-events";

    private final KafkaTemplate<String, ResidentCreatedEvent> kafkaTemplate;

    public void sendResidentCreated(Long residentId, String passportNumber) {
        ResidentCreatedEvent event =
                new ResidentCreatedEvent(residentId, passportNumber);

        kafkaTemplate.send(TOPIC, residentId.toString(), event);
    }
}