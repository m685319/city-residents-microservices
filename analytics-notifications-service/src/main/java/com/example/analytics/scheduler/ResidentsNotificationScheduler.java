package com.example.analytics.scheduler;

import com.example.analytics.client.ResidentsClient;
import com.example.analytics.dto.ResidentNotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResidentsNotificationScheduler {

    private final ResidentsClient residentsClient;

    @Scheduled(cron = "*/30 * * * * *")
    public void sendNotifications() {

        List<ResidentNotificationDto> residents =
                residentsClient.getAllResidents();

        residents.forEach(r ->
                log.info(
                        "[NOTIFICATION] Resident {} {} with passport {}",
                        r.getFirstName(),
                        r.getLastName(),
                        r.getPassportNumber()
                )
        );
    }
}
