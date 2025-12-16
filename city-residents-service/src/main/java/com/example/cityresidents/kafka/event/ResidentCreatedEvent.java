package com.example.cityresidents.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResidentCreatedEvent {
    private Long residentId;
    private String passportNumber;
}