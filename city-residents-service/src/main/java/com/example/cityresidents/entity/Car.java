package com.example.cityresidents.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "owner")
@Entity
@NamedQuery(
        name = "Car.findByOwnerId",
        query = "SELECT c FROM Car c WHERE c.owner.id = :ownerId"
)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String plateNumber;

    @Column(nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Resident owner;
}
