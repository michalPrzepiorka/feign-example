package com.example.feign.infrastructure.out.exchangeapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Y510p
 * @project feign
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteExchangeRatesDto {
    private final Map<String, BigDecimal> rates;
    private final String base;

    @JsonCreator
    public RemoteExchangeRatesDto(
            @JsonProperty("rates") Map<String, BigDecimal> rates,
            @JsonProperty("base") String base
    ) {
        this.rates = rates;
        this.base = base;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }
}
