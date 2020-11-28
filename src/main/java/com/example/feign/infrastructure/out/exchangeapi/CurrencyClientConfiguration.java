package com.example.feign.infrastructure.out.exchangeapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.net.URI;

@ConstructorBinding
@ConfigurationProperties(prefix = "external-services.exchange-rates-api")
@AllArgsConstructor
@Getter
public class CurrencyClientConfiguration {
    private final URI host;
}
