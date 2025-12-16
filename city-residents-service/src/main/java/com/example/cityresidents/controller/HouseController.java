package com.example.cityresidents.controller;

import com.example.cityresidents.domain.House;
import com.example.cityresidents.dto.HouseCreateDto;
import com.example.cityresidents.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    public ResponseEntity<House> create(@RequestBody HouseCreateDto dto) {
        House created = houseService.create(dto);
        URI location = URI.create("/houses/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        houseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
