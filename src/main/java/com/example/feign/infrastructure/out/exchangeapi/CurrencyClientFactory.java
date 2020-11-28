package com.example.feign.infrastructure.out.exchangeapi;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.stereotype.Component;

/**
 * @author Y510p
 * @project feign
 **/

@Component
public class CurrencyClientFactory {
    private final CurrencyClientConfiguration currencyClientConfiguration;

    public CurrencyClientFactory(CurrencyClientConfiguration currencyClientConfiguration) {
        this.currencyClientConfiguration = currencyClientConfiguration;
    }

    public CurrencyClient createClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(CurrencyClient.class, currencyClientConfiguration.getHost().toString());
    }
}
