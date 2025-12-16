package com.example.cityresidents.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "owners")
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "houses", fetch = FetchType.LAZY)
    private List<Resident> owners = new ArrayList<>();

    public void addOwner(Resident r) {
        owners.add(r);
        if (!r.getHouses().contains(this)) r.getHouses().add(this);
    }

    public void removeOwner(Resident r) {
        owners.remove(r);
        r.getHouses().remove(this);
    }
}
