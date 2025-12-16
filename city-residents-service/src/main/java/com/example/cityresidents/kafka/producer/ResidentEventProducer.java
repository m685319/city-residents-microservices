package com.example.cityresidents.kafka.producer;

import com.example.cityresidents.kafka.event.ResidentCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResidentEventProducer {

    private static final String TOPIC = "resident-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendResidentCreated(Long residentId, String passportNumber) {
        try {
            ResidentCreatedEvent event =
                    new ResidentCreatedEvent(residentId, passportNumber);

            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, residentId.toString(), payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize ResidentCreatedEvent", e);
        }
    }
}
