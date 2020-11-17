package com.example.feign.infrastructure.out.exchangeapi;

import com.example.feign.domain.ExchangeRates;
import com.example.feign.domain.ExchangeRatesProvider;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class ExternalClientBasedExchangeRatesProviderAdapter implements ExchangeRatesProvider {

    private final CurrencyClient currencyClient;

    public ExternalClientBasedExchangeRatesProviderAdapter(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public ExchangeRates getFor(Currency currency) {
        CurrencyResponse currencyResponse = currencyClient.findByBase(currency.getCurrencyCode());



        return new ExchangeRates(currency, currencyResponse.getRates());
    }
}
