package com.example.cityresidents.repo;

import com.example.cityresidents.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByOwnerId(Long ownerId);
}
