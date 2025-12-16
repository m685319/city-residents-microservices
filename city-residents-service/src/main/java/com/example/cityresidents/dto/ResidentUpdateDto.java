package com.example.cityresidents.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResidentUpdateDto {
    @NotBlank
    private String passportNumber;
}
