package com.example.feign.domain;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

/**
 * @author Y510p
 * @project feign
 **/

public class ExchangeRates {
    private final Currency currency;
    private final Map<String, BigDecimal> rates;

    public ExchangeRates(Currency currency, Map<String, BigDecimal> rates) {
        this.currency = currency;
        this.rates = rates;
    }

    public Rate rateTo(Currency targetCurrency) {
        return Optional.of(targetCurrency.getCurrencyCode())
                .map(rates::get)
                .map(rate -> new Rate(rate, targetCurrency))
                .orElseThrow(() -> new RuntimeException(String.format("Currency code %s not available", targetCurrency.getCurrencyCode())));
    }
}
