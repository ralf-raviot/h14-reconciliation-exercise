package it.h14.backend.domain;

import it.h14.backend.entity.PositionEntity;
import jakarta.annotation.Nullable;

/**
 * DTO for {@link PositionEntity}
 */
public record Position(Security security,
                       Double quantity,
                       PositionOrigin origin,
                       @Nullable Bank bank, /*Only used if origin = BANK*/
                       @Nullable String portfolioId,
                       @Nullable String clientId) {
}
