package com.example.cityresidents.mapper;

import com.example.cityresidents.entity.Car;
import com.example.cityresidents.dto.CarCreateDto;
import com.example.cityresidents.dto.CarResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toEntity(CarCreateDto dto);

    @Mapping(source = "owner.id", target = "ownerId")
    CarResponseDto toDto(Car car);
}