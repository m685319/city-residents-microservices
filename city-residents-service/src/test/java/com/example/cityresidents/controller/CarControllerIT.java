package com.example.cityresidents.controller;

import com.example.cityresidents.dto.CarCreateDto;
import com.example.cityresidents.entity.Passport;
import com.example.cityresidents.entity.Resident;
import com.example.cityresidents.repo.ResidentRepository;
import com.example.shared.enums.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Transactional
class CarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResidentRepository residentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Long createResident() {
        Resident resident = new Resident();
        resident.setFirstName("Ivan");
        resident.setLastName("Ivanov");
        resident.setGender(Gender.M);

        Passport passport = new Passport();
        passport.setPassportNumber("123456");

        resident.setPassport(passport);

        return residentRepository.save(resident).getId();
    }

    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void createCar_shouldReturn201() throws Exception {
        Long ownerId = createResident();

        CarCreateDto dto = new CarCreateDto();
        dto.setModel("Toyota");
        dto.setPlateNumber("A123BC");

        mockMvc.perform(post("/residents/{ownerId}/cars", ownerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value("Toyota"));
    }

    @Test
    void getCarsByOwner_shouldReturnList() throws Exception {
        Long ownerId = 1L;

        mockMvc.perform(get("/residents/{ownerId}/cars", ownerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
