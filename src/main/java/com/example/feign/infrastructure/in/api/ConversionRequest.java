package com.example.feign.infrastructure.in.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author Y510p
 * @project feign
 **/

public class ConversionRequest {
    public BigDecimal inputAmount;
    public String inputCurrency;
    public String targetCurrency;


    @JsonCreator
    public ConversionRequest(
            @JsonProperty("inputAmount") BigDecimal inputAmount,
            @JsonProperty("inputCurrency") String inputCurrency,
            @JsonProperty("targetCurrency") String targetCurrency
    ) {
        this.inputAmount = inputAmount;
        this.inputCurrency = inputCurrency;
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getInputAmount() {
        return inputAmount;
    }

    public String getInputCurrency() {
        return inputCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }
}
