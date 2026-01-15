package com.example.analytics.client.feign;

import com.example.analytics.client.ResidentsClient;
import com.example.analytics.dto.ResidentNotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Profile("feign")
@FeignClient(
        name = "residents-client",
        url = "${app.residents.base-url}"
)
public interface FeignResidentsClient extends ResidentsClient {

    @Override
    @GetMapping("/residents")
    List<ResidentNotificationDto> getAllResidents();
}
