package com.example.cityresidents.repo;

import com.example.cityresidents.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}