package com.example.cityresidents.controller;

import com.example.cityresidents.dto.CarCreateDto;
import com.example.cityresidents.dto.CarResponseDto;
import com.example.cityresidents.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/residents/{ownerId}/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponseDto> create(@PathVariable @Min(1) Long ownerId,
                                                 @RequestBody @Valid CarCreateDto dto) {
        CarResponseDto created = carService.createCar(ownerId, dto);
        URI location = URI.create("/residents/" + ownerId + "/cars/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDto>> listByOwner(@PathVariable @Min(1) Long ownerId) {
        List<CarResponseDto> list = carService.getCarsByOwner(ownerId);
        return ResponseEntity.ok(list);
    }
}
