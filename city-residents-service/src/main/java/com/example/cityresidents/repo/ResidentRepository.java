package com.example.cityresidents.repo;

import com.example.cityresidents.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    @Query("""
    SELECT DISTINCT r
    FROM Resident r
    JOIN r.houses h
    WHERE h.address LIKE CONCAT(:street, '%')
""")
    List<Resident> findOwnersByStreet(@Param("street") String street);
}
