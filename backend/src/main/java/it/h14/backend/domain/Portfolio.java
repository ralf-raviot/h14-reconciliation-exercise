package it.h14.backend.domain;

import jakarta.annotation.Nullable;

import java.util.Map;

/**
 * DTO for {@link it.h14.backend.entity.PortfolioEntity}
 */
public record Portfolio(String id,
                        @Nullable String name,/*Used default name if null*/
                        String clientId,
                        Map<Security, ConsolidatedPosition> positions) {
}
