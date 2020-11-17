package com.example.feign.infrastructure.out.exchangeapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyClientConfiguration {
    @Bean
    public CurrencyClient currencyClient(CurrencyClientFactory clientFactory) {
        return clientFactory.createClient();
    }
}
