package com.example.cityresidents.mapper;

import com.example.cityresidents.domain.House;
import com.example.cityresidents.dto.HouseCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    House toEntity(HouseCreateDto dto);
}
