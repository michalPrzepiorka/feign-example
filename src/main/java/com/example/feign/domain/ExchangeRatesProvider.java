package com.example.feign.domain;

import java.util.Currency;

public interface ExchangeRatesProvider {
    ExchangeRates getFor(Currency currency);
}
