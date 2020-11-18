package com.example.feign.domain;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Y510p
 * @project feign
 **/

public class Rate {
    private final BigDecimal rate;
    private final Currency targetCurrency;

    public Rate(BigDecimal rate, Currency targetCurrency) {
        this.rate = rate;
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }
}
