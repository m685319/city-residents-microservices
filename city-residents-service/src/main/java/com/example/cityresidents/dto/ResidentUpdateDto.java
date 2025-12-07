package com.example.cityresidents.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResidentUpdateDto {
    @Size(max = 100)
    @NotBlank
    private String passportNumber;
}
