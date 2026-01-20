package com.example.cityresidents.mapper;

import com.example.cityresidents.entity.Resident;
import com.example.shared.dto.ResidentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResidentMapper {

    @Mapping(source = "passport", target = "passportDto")
    ResidentDto toDto(Resident resident);

    @Mapping(source = "passportDto", target = "passport")
    Resident toEntity(ResidentDto dto);
}