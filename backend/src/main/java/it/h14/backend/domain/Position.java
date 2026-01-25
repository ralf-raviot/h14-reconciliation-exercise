package it.h14.backend.domain;

import jakarta.annotation.Nullable;

public record Position(String id,
                       Security security,
                       Double quantity,
                       PositionOrigin origin,
                       @Nullable Bank bank /*Only used if origin = BANK*/) {
}
