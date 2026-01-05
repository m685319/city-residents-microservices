package com.example.cityresidents.kafka.producer;

import com.example.cityresidents.entity.Resident;
import com.example.shared.event.ResidentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResidentEventProducer {

    private static final String TOPIC = "resident-events";

    private final KafkaTemplate<String, ResidentCreatedEvent> kafkaTemplate;

    public void sendResidentCreated(Resident resident) {

        ResidentCreatedEvent event =
                new ResidentCreatedEvent(
                        resident.getId(),
                        resident.getPassport().getPassportNumber(),
                        resident.getGender()
                );

        kafkaTemplate.send(TOPIC, resident.getId().toString(), event);
    }
}