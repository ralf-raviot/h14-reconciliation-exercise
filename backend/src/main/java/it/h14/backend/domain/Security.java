package it.h14.backend.domain;

import java.util.Currency;

/**
 * DTO for {@link it.h14.backend.entity.SecurityEntity}
 */
public record Security(String isin,
                       String name,
                       Currency currency,
                       Double marketPrice) {

    public boolean equals(Security other) {
        return isin.equals(other.isin);
    }
}
