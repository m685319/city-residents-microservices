package com.example.cityresidents.controller;

import com.example.cityresidents.service.ResidentService;
import com.example.shared.api.ResidentsApi;
import com.example.shared.dto.ResidentDto;
import com.example.shared.dto.ResidentNotificationDto;
import com.example.shared.dto.ResidentUpdateDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Tag(name = "Residents", description = "Resident management API")
@Validated
@RestController
@RequiredArgsConstructor
public class ResidentController implements ResidentsApi {

    private final ResidentService residentService;

    @Override
    public ResponseEntity<ResidentDto> create(ResidentDto dto) {
        ResidentDto created = residentService.createResident(dto);
        URI location = URI.create("/residents/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public List<ResidentNotificationDto> getResidentsForNotifications() {
        return residentService.getResidentsForNotifications();
    }

    @Override
    public ResponseEntity<ResidentDto> get(Long id) {
        return ResponseEntity.ok(residentService.getById(id));
    }

    @Override
    public ResponseEntity<List<ResidentDto>> getOwnersByStreet(String street) {
        return ResponseEntity.ok(residentService.getOwnersByStreet(street));
    }

    @Override
    public ResponseEntity<ResidentDto> update(Long id, ResidentUpdateDto dto) {
        return ResponseEntity.ok(residentService.updateResident(id, dto));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }
}