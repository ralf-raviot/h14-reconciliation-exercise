package it.h14.backend.entity;

import it.h14.backend.domain.Bank;
import it.h14.backend.domain.PositionOrigin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "position_entity")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private SecurityEntity security;

    @Column(name = "quantity")
    private Double quantity;

    @Enumerated
    @Column(name = "origin")
    private PositionOrigin origin;

    @Enumerated
    @Column(name = "bank")
    private Bank bank;

}