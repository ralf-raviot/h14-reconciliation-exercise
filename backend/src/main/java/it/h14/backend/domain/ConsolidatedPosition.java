package it.h14.backend.domain;

import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public record ConsolidatedPosition(Security security,
                                   @Nullable Position internalPosition,
                                   List<Position> bankPositions) {

    public ConsolidationStatus computeConsolidationStatus() {
        if (internalPosition == null) {
            return ConsolidationStatus.UNKNOWN_POSITION;
        }
        if (bankPositions.isEmpty()) {
            return ConsolidationStatus.MISSING_BANK_POSITION;
        }
        if (!Objects.equals(internalPosition.quantity(), getTotalBankQuantity())) {
            return ConsolidationStatus.NOT_CONSOLIDATED;
        }
        return ConsolidationStatus.CONSOLIDATED;
    }

    private Double getTotalBankQuantity() {
        return bankPositions.stream().mapToDouble(Position::quantity).sum();
    }

    public enum ConsolidationStatus {
        CONSOLIDATED,
        NOT_CONSOLIDATED,
        UNKNOWN_POSITION,
        MISSING_BANK_POSITION
    }
}
