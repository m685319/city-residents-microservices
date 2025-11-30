package com.example.cityresidents.service;

import com.example.cityresidents.domain.Passport;
import com.example.cityresidents.domain.Resident;
import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.repo.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;

    @Transactional
    public Resident createResident(ResidentDto dto) {
        Passport p = new Passport("");
        Resident r = new Resident(p);
        return residentRepository.save(r);
    }

    public Resident getById(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found: " + id));
    }
}