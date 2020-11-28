package com.example.feign.infrastructure.out.exchangeapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Y510p
 * @project feign
 **/

@Configuration
public class CurrencyClientProvider {
    @Bean
    public CurrencyClient currencyClient(CurrencyClientFactory clientFactory) {
        return clientFactory.createClient();
    }
}
