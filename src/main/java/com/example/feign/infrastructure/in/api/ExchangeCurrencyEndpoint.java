package com.example.feign.infrastructure.in.api;

import com.example.feign.domain.Exchanger;
import com.example.feign.domain.Money;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

/**
 * @author Y510p
 * @project feign
 **/

@RestController
public class ExchangeCurrencyEndpoint {

    private final Exchanger exchanger;

    public ExchangeCurrencyEndpoint(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @PostMapping("/exchange")
    public ConversionResponse exchange(@RequestBody ConversionRequest conversionRequest) {
        Money input = toInputMoney(conversionRequest);
        Currency targetCurrency = toInputCurrency(conversionRequest);

        Money result = exchanger.exchange(input, targetCurrency);

        return toResponse(result);
    }

    private ConversionResponse toResponse(Money input) {
        return new ConversionResponse(
                String.format("%.2f %s", input.getAmount(), input.getCurrency().getCurrencyCode())
        );
    }

    private Currency toInputCurrency(ConversionRequest conversionRequest) {
        return Currency.getInstance(conversionRequest.targetCurrency);
    }

    private Money toInputMoney(ConversionRequest conversionRequest) {
        return new Money(
                conversionRequest.inputAmount,
                Currency.getInstance(conversionRequest.inputCurrency)
        );
    }
}
