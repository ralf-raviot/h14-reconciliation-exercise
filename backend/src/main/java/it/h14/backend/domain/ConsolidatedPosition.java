package it.h14.backend.domain;

import jakarta.annotation.Nullable;

import java.util.List;

public record ConsolidatedPosition(Security security,
                                   @Nullable Position internalPosition,
                                   List<Position> bankPositions) {

    public enum ConsolidationStatus {
        CONSOLIDATED,
        NOT_CONSOLIDATED,
        UNKNOWN_POSITION
    }
}
