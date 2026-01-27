package it.h14.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@Entity
@Table(schema = "PUBLIC", name = "security")
public class SecurityEntity {

    @Id
    @Column(name = "isin", nullable = false, unique = true)
    private String isin;

    @Column(name = "name")
    private String name;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "market_price")
    private Double marketPrice;

}