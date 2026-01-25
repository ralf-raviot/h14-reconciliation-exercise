package it.h14.backend.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link ConsolidatedPosition} class.
 * This class tests the logic for computing the consolidation status between internal and bank positions.
 */
class ConsolidatedPositionTest {

    /**
     * Tests {@link ConsolidatedPosition#computeConsolidationStatus()}.
     * Verifies that the status is UNKNOWN_POSITION when the internal position is null.
     */
    @Test
    @DisplayName("Should return UNKNOWN_POSITION when internalPosition is null")
    void computeConsolidationStatus_NullInternalPosition_ReturnsUnknownPosition() {
        // Given
        Security security = new Security("ISIN123", "Security Name", Currency.getInstance("EUR"), 100.0);
        ConsolidatedPosition consolidatedPosition = new ConsolidatedPosition(
                security,
                null,
                List.of(new Position("P1", security, 10.0, PositionOrigin.BANK, Bank.BANCAPOSTALE))
        );

        // When
        ConsolidatedPosition.ConsolidationStatus status = consolidatedPosition.computeConsolidationStatus();

        // Then
        assertEquals(ConsolidatedPosition.ConsolidationStatus.UNKNOWN_POSITION, status);
    }

    /**
     * Tests {@link ConsolidatedPosition#computeConsolidationStatus()}.
     * Verifies that the status is MISSING_BANK_POSITION when the bank positions list is empty.
     */
    @Test
    @DisplayName("Should return MISSING_BANK_POSITION when bankPositions list is empty")
    void computeConsolidationStatus_EmptyBankPositions_ReturnsMissingBankPosition() {
        // Given
        Security security = new Security("ISIN123", "Security Name", Currency.getInstance("EUR"), 100.0);
        Position internalPosition = new Position("P1", security, 10.0, PositionOrigin.INTERNAL, null);
        ConsolidatedPosition consolidatedPosition = new ConsolidatedPosition(
                security,
                internalPosition,
                Collections.emptyList()
        );

        // When
        ConsolidatedPosition.ConsolidationStatus status = consolidatedPosition.computeConsolidationStatus();

        // Then
        assertEquals(ConsolidatedPosition.ConsolidationStatus.MISSING_BANK_POSITION, status);
    }

    /**
     * Tests {@link ConsolidatedPosition#computeConsolidationStatus()}.
     * Verifies that the status is NOT_CONSOLIDATED when the internal position quantity
     * does not match the sum of quantities in the bank positions.
     */
    @Test
    @DisplayName("Should return NOT_CONSOLIDATED when internal quantity does not match total bank quantity")
    void computeConsolidationStatus_DifferentQuantities_ReturnsNotConsolidated() {
        // Given
        Security security = new Security("ISIN123", "Security Name", Currency.getInstance("EUR"), 100.0);
        Position internalPosition = new Position("P1", security, 10.0, PositionOrigin.INTERNAL, null);
        List<Position> bankPositions = List.of(
                new Position("P2", security, 4.0, PositionOrigin.BANK, Bank.BANCAPOSTALE),
                new Position("P3", security, 5.0, PositionOrigin.BANK, Bank.NEOBANCA)
        );
        ConsolidatedPosition consolidatedPosition = new ConsolidatedPosition(
                security,
                internalPosition,
                bankPositions
        );

        // When
        ConsolidatedPosition.ConsolidationStatus status = consolidatedPosition.computeConsolidationStatus();

        // Then
        assertEquals(ConsolidatedPosition.ConsolidationStatus.NOT_CONSOLIDATED, status);
    }

    /**
     * Tests {@link ConsolidatedPosition#computeConsolidationStatus()}.
     * Verifies that the status is CONSOLIDATED when the internal position quantity
     * exactly matches the sum of quantities in the bank positions.
     */
    @Test
    @DisplayName("Should return CONSOLIDATED when internal quantity matches total bank quantity")
    void computeConsolidationStatus_MatchingQuantities_ReturnsConsolidated() {
        // Given
        Security security = new Security("ISIN123", "Security Name", Currency.getInstance("EUR"), 100.0);
        Position internalPosition = new Position("P1", security, 10.0, PositionOrigin.INTERNAL, null);
        List<Position> bankPositions = List.of(
                new Position("P2", security, 4.0, PositionOrigin.BANK, Bank.BANCAPOSTALE),
                new Position("P3", security, 6.0, PositionOrigin.BANK, Bank.NEOBANCA)
        );
        ConsolidatedPosition consolidatedPosition = new ConsolidatedPosition(
                security,
                internalPosition,
                bankPositions
        );

        // When
        ConsolidatedPosition.ConsolidationStatus status = consolidatedPosition.computeConsolidationStatus();

        // Then
        assertEquals(ConsolidatedPosition.ConsolidationStatus.CONSOLIDATED, status);
    }
}