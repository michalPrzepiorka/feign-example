package com.example.feign.domain;

import java.util.Currency;

/**
 * @author Y510p
 * @project feign
 **/

public interface ExchangeRatesProvider {
    ExchangeRates getFor(Currency currency);
}
