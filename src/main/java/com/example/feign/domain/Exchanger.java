package com.example.feign.domain;

import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class Exchanger {
    private final ExchangeRatesProvider exchangeRatesProvider;

    public Exchanger(ExchangeRatesProvider exchangeRatesProvider) {
        this.exchangeRatesProvider = exchangeRatesProvider;
    }

    public Money exchange(Money input, Currency targetCurrency) {
        ExchangeRates inputCurrencyExchangeRates = exchangeRatesProvider.getFor(input.getCurrency());
        Rate rate = inputCurrencyExchangeRates.rateTo(targetCurrency);

        return input.toCurrency(rate);
    }
}
