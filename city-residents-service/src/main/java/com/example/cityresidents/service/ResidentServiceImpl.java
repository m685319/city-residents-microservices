package com.example.cityresidents.service;

import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import com.example.cityresidents.entity.Resident;
import com.example.cityresidents.exception.EntityNotFoundException;
import com.example.cityresidents.kafka.producer.ResidentEventProducer;
import com.example.cityresidents.mapper.ResidentMapper;
import com.example.cityresidents.mapper.ResidentNotificationMapper;
import com.example.cityresidents.repo.ResidentRepository;
import com.example.shared.dto.ResidentNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;
    private final ResidentEventProducer residentEventProducer;
    private final ResidentNotificationMapper notificationMapper;

    @Override
    public ResidentDto createResident(ResidentDto dto) {
        Resident r = residentMapper.toEntity(dto);
        Resident saved = residentRepository.save(r);
        residentEventProducer.sendResidentCreated(saved);
        return residentMapper.toDto(saved);
    }

    @Override
    public ResidentDto getById(Long id) {
        Resident resident =  residentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resident not found: " + id));
        return residentMapper.toDto(resident);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResidentNotificationDto> getResidentsForNotifications() {
        return residentRepository.findAllForNotifications().stream()
                .map(notificationMapper::toDto)
                .toList();
    }

    @Override
    public List<ResidentDto> getOwnersByStreet(String street) {
        return residentRepository.findOwnersByStreet(street)
                .stream()
                .map(residentMapper::toDto)
                .toList();
    }

    @Override
    public List<String> getMalePassportsByLastNamePrefix(String letter) {
        return residentRepository.findMalePassportNumbersByLastNamePrefix(letter);
    }

    @Override
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

    @Override
    @Transactional
    public void deleteResident(Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found: " + id));

        residentRepository.delete(resident);
    }
}