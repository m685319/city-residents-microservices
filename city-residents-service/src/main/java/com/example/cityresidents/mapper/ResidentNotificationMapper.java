package com.example.cityresidents.mapper;

import com.example.cityresidents.dto.notification.ResidentNotificationInternalDto;
import com.example.shared.dto.ResidentNotificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResidentNotificationMapper {

    ResidentNotificationDto toDto(ResidentNotificationInternalDto view);
}
