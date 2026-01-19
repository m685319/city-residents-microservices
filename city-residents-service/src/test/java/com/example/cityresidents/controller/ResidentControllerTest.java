package com.example.cityresidents.controller;


import com.example.cityresidents.dto.PassportDto;
import com.example.cityresidents.dto.ResidentDto;
import com.example.cityresidents.dto.ResidentUpdateDto;
import com.example.cityresidents.exception.EntityNotFoundException;
import com.example.cityresidents.mapper.ResidentNotificationMapper;
import com.example.cityresidents.service.ResidentService;
import com.example.shared.enums.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResidentController.class)
@Import(ResidentNotificationMapper.class)
class ResidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResidentService residentService;

    @MockitoBean
    private ResidentNotificationMapper notificationMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createResident_shouldReturn201() throws Exception {
        ResidentDto input = new ResidentDto();
        input.setFirstName("Ivan");
        input.setLastName("Ivanov");
        input.setGender(Gender.M);

        ResidentDto created = new ResidentDto();
        created.setId(1L);
        created.setFirstName("Ivan");
        created.setLastName("Ivanov");
        created.setGender(Gender.M);

        when(residentService.createResident(any()))
                .thenReturn(created);

        mockMvc.perform(post("/residents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/residents/1"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getResident_shouldReturn200() throws Exception {
        ResidentDto dto = new ResidentDto();
        dto.setId(1L);

        when(residentService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/residents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getResident_shouldReturn404_whenNotFound() throws Exception {
        when(residentService.getById(99L))
                .thenThrow(new EntityNotFoundException("Resident not found: 99"));

        mockMvc.perform(get("/residents/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateResident_shouldReturn200() throws Exception {
        // given
        ResidentUpdateDto update = new ResidentUpdateDto();
        update.setPassportNumber("NEW-123456");

        PassportDto passportDto = new PassportDto();
        passportDto.setPassportNumber("NEW-123456");

        ResidentDto updated = new ResidentDto();
        updated.setId(1L);
        updated.setPassportDto(passportDto);

        when(residentService.updateResident(eq(1L), any()))
                .thenReturn(updated);

        // when + then
        mockMvc.perform(put("/residents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.passportDto.passportNumber")
                        .value("NEW-123456"));
    }

    @Test
    void updateResident_shouldReturn422_whenPassportNumberBlank() throws Exception {
        ResidentUpdateDto update = new ResidentUpdateDto();
        update.setPassportNumber("");

        mockMvc.perform(put("/residents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteResident_shouldReturn204() throws Exception {
        doNothing().when(residentService).deleteResident(1L);

        mockMvc.perform(delete("/residents/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getResident_shouldReturn400_whenIdInvalid() throws Exception {
        mockMvc.perform(get("/residents/0"))
                .andExpect(status().isBadRequest());
    }
}