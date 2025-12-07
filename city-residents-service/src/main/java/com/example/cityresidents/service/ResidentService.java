package com.example.cityresidents.service;

import com.example.cityresidents.domain.Passport;
import com.example.cityresidents.domain.Resident;
import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import com.example.cityresidents.mapper.ResidentMapper;
import com.example.cityresidents.repo.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;

    @Transactional
    public ResidentDto createResident(ResidentDto dto) {
        Passport p = new Passport("");
        Resident r = new Resident(p);
        Resident saved = residentRepository.save(r);
        return residentMapper.toDto(saved);
    }

    public ResidentDto getById(Long id) {
        Resident resident =  residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found: " + id));
        return residentMapper.toDto(resident);
    }

    @Transactional
    public ResidentDto updateResident(Long id, ResidentUpdateDto dto) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found: " + id));

        if (dto.getPassportNumber() != null) {
            resident.getPassport().setPassportNumber(dto.getPassportNumber());
        }

        Resident updated = residentRepository.save(resident);
        return residentMapper.toDto(updated);
    }

    @Transactional
    public void deleteResident(Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found: " + id));

        residentRepository.delete(resident);
    }
}