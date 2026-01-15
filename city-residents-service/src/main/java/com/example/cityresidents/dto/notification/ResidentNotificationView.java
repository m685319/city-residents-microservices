package com.example.cityresidents.dto.notification;

import lombok.Data;

@Data
public class ResidentNotificationView {
    private final String firstName;
    private final String lastName;
    private final String passportNumber;
}