package com.example.cityresidents.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Car(String plateNumber, String model) {
        this.plateNumber = plateNumber;
        this.model = model;
    }
}
