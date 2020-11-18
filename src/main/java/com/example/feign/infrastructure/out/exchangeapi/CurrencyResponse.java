package com.example.feign.infrastructure.out.exchangeapi;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyResponse {
    private final Map<String, BigDecimal> rates;
    private final String base;

    public CurrencyResponse(Map<String, BigDecimal> rates, String base) {
        this.rates = rates;
        this.base = base;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }
}
