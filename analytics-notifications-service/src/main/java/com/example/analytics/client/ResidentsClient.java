package com.example.analytics.client;

import com.example.analytics.dto.ResidentNotificationDto;

import java.util.List;

public interface ResidentsClient {
    List<ResidentNotificationDto> getAllResidents();
}
