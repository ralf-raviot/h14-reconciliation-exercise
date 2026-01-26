package it.h14.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "consolidated_position_entity")
public class ConsolidatedPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn
    private SecurityEntity security;

    @ManyToOne
    @JoinColumn
    private PositionEntity internalPosition;

    @OneToMany(orphanRemoval = true)
    private Set<PositionEntity> bankPositions = new LinkedHashSet<>();

}