package com.example.cityresidents.controller;

import com.example.cityresidents.domain.Resident;
import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import com.example.cityresidents.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<Resident> create(@RequestBody ResidentDto dto) {
        Resident created = residentService.createResident(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resident> get(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resident> update(@PathVariable Long id, @RequestBody ResidentUpdateDto dto) {
        Resident updated = residentService.updateResident(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }
}
