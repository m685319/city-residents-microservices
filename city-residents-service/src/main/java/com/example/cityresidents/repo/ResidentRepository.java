package com.example.cityresidents.repo;

import com.example.cityresidents.dto.notification.ResidentNotificationView;
import com.example.cityresidents.entity.Resident;
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

    @Query(
            value = """
        SELECT p.passport_number
        FROM resident r
        JOIN passport p ON r.passport_id = p.id
        WHERE r.gender = 'M'
        AND r.last_name ILIKE CONCAT(:letter, '%')
    """,
            nativeQuery = true
    )
    List<String> findMalePassportNumbersByLastNamePrefix(
            @Param("letter") String letter
    );

    @Query("""
    select new com.example.cityresidents.dto.notification.ResidentNotificationView(
        r.firstName,
        r.lastName,
        p.passportNumber
    )
    from Resident r
    join r.passport p
""")
    List<ResidentNotificationView> findAllForNotifications();
}