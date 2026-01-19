package com.example.cityresidents.controller;

import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import com.example.cityresidents.service.ResidentService;
import com.example.shared.api.ResidentsApi;
import com.example.shared.dto.ResidentNotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Tag(name = "Residents", description = "Resident management API")
@Validated
@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController implements ResidentsApi {

    private final ResidentService residentService;

    @Operation(
            summary = "Create resident",
            description = "Creates a new resident with passport"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Resident created"),
            @ApiResponse(responseCode = "422", description = "Validation error")
    })
    @PostMapping
    public ResponseEntity<ResidentDto> create(@RequestBody @Valid ResidentDto dto) {
        ResidentDto created = residentService.createResident(dto);
        URI location = URI.create("/residents/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public List<ResidentNotificationDto> getResidentsForNotifications() {
        return residentService.getResidentsForNotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDto> get(@PathVariable @Min(1) Long id) {
        ResidentDto dto = residentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<ResidentDto>> getOwnersByStreet(@RequestParam String street) {
        return ResponseEntity.ok(residentService.getOwnersByStreet(street));
    }

    @GetMapping("/passports/male")
    public ResponseEntity<List<String>> getMalePassports(@RequestParam String letter) {
        return ResponseEntity.ok(
                residentService.getMalePassportsByLastNamePrefix(letter)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentDto> update(@PathVariable @Min(1) Long id, @RequestBody @Valid ResidentUpdateDto dto) {
        ResidentDto updated = residentService.updateResident(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }
}
