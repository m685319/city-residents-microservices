package com.example.shared.dto;

import com.example.shared.enums.Gender;
import lombok.Data;

@Data
public class ResidentDto {
    private Long id;
    private PassportDto passportDto;
    private String firstName;
    private String lastName;
    private Gender gender;
}