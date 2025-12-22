package com.example.cityresidents.service;

import com.example.cityresidents.dto.HouseCreateDto;
import com.example.cityresidents.entity.House;
import com.example.cityresidents.entity.Resident;
import com.example.cityresidents.exception.EntityNotFoundException;
import com.example.cityresidents.mapper.HouseMapper;
import com.example.cityresidents.repo.HouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseServiceTest {

    @Mock
    HouseRepository houseRepository;

    @Mock
    HouseMapper houseMapper;

    @InjectMocks
    HouseService houseService;

    @Test
    void create_shouldMapAndSaveHouse() {
        // given
        HouseCreateDto dto = new HouseCreateDto();
        dto.setAddress("Lenina 10");

        House mappedHouse = new House();
        mappedHouse.setAddress("Lenina 10");

        House savedHouse = new House();
        savedHouse.setId(1L);
        savedHouse.setAddress("Lenina 10");

        when(houseMapper.toEntity(dto)).thenReturn(mappedHouse);
        when(houseRepository.save(mappedHouse)).thenReturn(savedHouse);

        // when
        House result = houseService.create(dto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Lenina 10", result.getAddress());

        verify(houseMapper).toEntity(dto);
        verify(houseRepository).save(mappedHouse);
    }

    @Test
    void delete_shouldRemoveHouseAndClearOwners() {
        // given
        House house = new House();
        house.setId(1L);

        Resident owner = new Resident();
        owner.setHouses(new ArrayList<>(List.of(house)));

        house.setOwners(new ArrayList<>(List.of(owner)));

        when(houseRepository.findById(1L)).thenReturn(Optional.of(house));

        // when
        houseService.delete(1L);

        // then
        assertTrue(house.getOwners().isEmpty());
        assertTrue(owner.getHouses().isEmpty());

        verify(houseRepository).delete(house);
    }

    @Test
    void delete_shouldThrowException_whenHouseNotFound() {
        // given
        when(houseRepository.findById(99L)).thenReturn(Optional.empty());

        // when + then
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class,
                () -> houseService.delete(99L)
        );

        assertEquals("House not found: 99", ex.getMessage());

        verify(houseRepository, never()).delete(any());
    }
}