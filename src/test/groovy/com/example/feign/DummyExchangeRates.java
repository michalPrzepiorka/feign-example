package com.example.feign;

import com.example.feign.domain.ExchangeRates;
import com.example.feign.domain.ExchangeRatesProvider;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

/**
 * @author Y510p
 * @project feign
 **/

public class DummyExchangeRates implements ExchangeRatesProvider {

    @Override
    public ExchangeRates getFor(Currency currency) {
        return new ExchangeRates(currency, Map.of("EUR", BigDecimal.valueOf(4.5)));
    }
}
