package it.h14.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "portfolio_entity")
public class PortfolioEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "client_id")
    private String clientId;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ConsolidatedPositionEntity> positions = new LinkedHashSet<>();

}