package com.example.feign.clients;

import com.example.feign.models.external.CurrencyResponse;
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
