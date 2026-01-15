package com.example.analytics.client.rest;

import com.example.analytics.client.ResidentsClient;
import com.example.analytics.client.ResidentsClientProperties;
import com.example.analytics.dto.ResidentNotificationDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@Profile("rest")
public class RestResidentsClient implements ResidentsClient {

    private final RestClient restClient;

    public RestResidentsClient(ResidentsClientProperties properties) {
        this.restClient = RestClient.builder()
                .baseUrl(properties.getBaseUrl())
                .build();
    }

    @Override
    public List<ResidentNotificationDto> getAllResidents() {
        return restClient
                .get()
                .uri("/residents/notifications")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<>() {});
    }
}