package com.example.cityresidents.dto;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String plateNumber;
    private String model;
    private Long ownerId;
}