package com.example.cityresidents.service;

import com.example.cityresidents.domain.Car;
import com.example.cityresidents.domain.Resident;
import com.example.cityresidents.dto.CarCreateDto;
import com.example.cityresidents.dto.CarResponseDto;
import com.example.cityresidents.mapper.CarMapper;
import com.example.cityresidents.repo.CarRepository;
import com.example.cityresidents.repo.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ResidentRepository residentRepository;
    private final CarMapper carMapper;

    @Transactional
    public CarResponseDto createCar(Long ownerId, CarCreateDto dto) {
        Resident owner = residentRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found: " + ownerId));

        Car car = carMapper.toEntity(dto);
        car.setOwner(owner);
        carRepository.save(car);

        return carMapper.toDto(car);
    }
}