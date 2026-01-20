package com.example.shared.api;

import com.example.shared.dto.ResidentDto;
import com.example.shared.dto.ResidentNotificationDto;
import com.example.shared.dto.ResidentUpdateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/residents")
@Validated
public interface ResidentsApi {

    @PostMapping
    ResponseEntity<ResidentDto> create(@RequestBody @Valid ResidentDto dto);

    @GetMapping("/notifications")
    List<ResidentNotificationDto> getResidentsForNotifications();

    @GetMapping("/{id}")
    ResponseEntity<ResidentDto> get(@PathVariable @Min(1) Long id);

    @GetMapping("/owners")
    ResponseEntity<List<ResidentDto>> getOwnersByStreet(@RequestParam String street);

    @PutMapping("/{id}")
    ResponseEntity<ResidentDto> update(@PathVariable Long id,
                                       @RequestBody @Valid ResidentUpdateDto dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
