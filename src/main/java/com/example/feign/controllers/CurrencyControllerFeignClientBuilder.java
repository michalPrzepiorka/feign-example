package com.example.feign.controllers;

import com.example.feign.clients.CurrencyClient;
import com.example.feign.models.external.CurrencyResponse;
import com.example.feign.models.internal.ConversionRequest;
import com.example.feign.models.internal.ConversionResponse;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Y510p
 * @project feign
 **/

@RestController
public class CurrencyControllerFeignClientBuilder {

    private CurrencyClient currencyClient = createClient(
            CurrencyClient.class,
            "https://api.exchangeratesapi.io"
    );

    private static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.FULL)
                .target(type, uri);
    }

    @PostMapping("/exchange")
    public ConversionResponse getEuroExchange(@RequestBody ConversionRequest conversionRequest) {
        CurrencyResponse pln = currencyClient.findByBase(conversionRequest.getInputCurrency());
        return new ConversionResponse(String.format("%.2f %s", conversionRequest.getInputAmount().multiply(pln.getRates().get(conversionRequest.getTargetCurrency())), conversionRequest.getTargetCurrency()));
    }
}
