package com.example.cityresidents.repo;

import com.example.cityresidents.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}