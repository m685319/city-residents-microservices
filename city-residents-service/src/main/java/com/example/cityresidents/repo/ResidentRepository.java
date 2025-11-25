package com.example.cityresidents.repo;

import com.example.cityresidents.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
