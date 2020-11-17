package com.example.feign.models.internal;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

/**
 * @author Y510p
 * @project feign
 **/

@Value
public class ConversionResponse {
    private String exchangedInput;
}
