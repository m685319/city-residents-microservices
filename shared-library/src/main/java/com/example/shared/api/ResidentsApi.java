package com.example.shared.api;

import com.example.shared.dto.ResidentNotificationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/residents")
public interface ResidentsApi {

    @GetMapping("/notifications")
    List<ResidentNotificationDto> getResidentsForNotifications();
}
