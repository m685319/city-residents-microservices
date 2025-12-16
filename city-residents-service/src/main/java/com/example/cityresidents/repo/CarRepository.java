package com.example.cityresidents.repo;

import com.example.cityresidents.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(name = "Car.findByOwnerId")
    List<Car> findByOwnerId(@Param("ownerId") Long ownerId);
}
