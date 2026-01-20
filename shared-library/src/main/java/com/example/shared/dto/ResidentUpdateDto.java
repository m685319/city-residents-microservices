package com.example.shared.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResidentUpdateDto {
    @NotBlank
    private String passportNumber;
}
