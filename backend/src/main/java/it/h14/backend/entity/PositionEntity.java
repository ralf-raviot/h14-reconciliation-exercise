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
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
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