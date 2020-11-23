package com.example.feign.infrastructure.out.exchangeapi;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author Y510p
 * @project feign
 **/

@Component
public class CurrencyClientFactory {
    private URI uri;

    public CurrencyClientFactory() {
        this.uri = URI.create("https://api.exchangeratesapi.io");
    }

    public CurrencyClient createClient(URI uri) {
        if (uri != null) {
            this.uri = uri;
        }
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(CurrencyClient.class, this.uri.toString());
    }
}
