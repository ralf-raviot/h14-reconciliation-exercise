package it.h14.backend.domain;

import java.util.Currency;

public record Security(String isin,
                       String name,
                       Currency currency,
                       Double marketPrice) {
}
