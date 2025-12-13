package com.example.cityresidents.mapper;

import com.example.cityresidents.domain.Resident;
import com.example.cityresidents.dto.ResidentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResidentMapper {

    @Mapping(source = "passport.id", target = "passportId")
    @Mapping(source = "passport.passportNumber", target = "passportNumber")
    ResidentDto toDto(Resident resident);

    Resident toEntity(ResidentDto dto);
}