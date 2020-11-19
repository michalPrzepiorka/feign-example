package com.example.feign.infrastructure.out.exchangeapi;

import feign.Param;
import feign.RequestLine;

/**
 * @author Y510p
 * @project feign
 **/

public interface CurrencyClient {
    @RequestLine("GET /latest?base={currency}")
    CurrencyResponse findByBase(@Param("currency") String base);
}
