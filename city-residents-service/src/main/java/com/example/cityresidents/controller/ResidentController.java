package com.example.cityresidents.controller;

import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import com.example.cityresidents.service.ResidentService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<ResidentDto> create(@RequestBody @Valid ResidentDto dto) {
        ResidentDto created = residentService.createResident(dto);
        URI location = URI.create("/residents/" + created.getId());
        return ResponseEntity.created(location).body(created);
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
