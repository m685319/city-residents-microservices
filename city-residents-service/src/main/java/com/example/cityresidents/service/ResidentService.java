package com.example.cityresidents.service;

import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResidentService {
    ResidentDto createResident(ResidentDto dto);

    ResidentDto getById(Long id);

    List<ResidentDto> getOwnersByStreet(String street);

    List<String> getMalePassportsByLastNamePrefix(String letter);

    @Transactional
    ResidentDto updateResident(Long id, ResidentUpdateDto dto);

    @Transactional
    void deleteResident(Long id);
}
