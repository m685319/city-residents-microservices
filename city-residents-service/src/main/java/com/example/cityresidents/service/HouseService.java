package com.example.cityresidents.service;

import com.example.cityresidents.domain.House;
import com.example.cityresidents.dto.HouseCreateDto;
import com.example.cityresidents.exception.EntityNotFoundException;
import com.example.cityresidents.mapper.HouseMapper;
import com.example.cityresidents.repo.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapper houseMapper;

    @Transactional
    public House create(HouseCreateDto dto) {
        House house = houseMapper.toEntity(dto);
        return houseRepository.save(house);
    }

    @Transactional
    public void delete(Long id) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("House not found: " + id));
        house.getOwners().forEach(owner -> owner.getHouses().remove(house));
        house.getOwners().clear();
        houseRepository.delete(house);
    }
}
