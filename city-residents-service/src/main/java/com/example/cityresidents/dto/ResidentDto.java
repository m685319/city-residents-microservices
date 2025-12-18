package com.example.cityresidents.dto;

import lombok.Data;

@Data
public class ResidentDto {
    private Long id;
    private PassportDto passportDto;
    private String firstName;
    private String lastName;
    private String gender;
}