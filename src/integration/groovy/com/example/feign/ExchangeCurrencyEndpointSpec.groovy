package com.example.feign

import io.restassured.response.ValidatableResponse
import org.hamcrest.Matchers

import static org.springframework.http.HttpStatus.OK

class ExchangeCurrencyEndpointSpec extends BaseIntegrationSpec implements SendRequestToServerAbility, ExchangeRatesApiStubAbility {
    def "when input and target currency are the same then should return provided amount instantly"() {
        given:
            whenGetLatestWasCalled {
                withBaseCurrencyCode 'PLN'
                respondWith {
                    withCode OK
                    withBody {
                        withBase 'PLN'
                        withUSD '3.50'
                        withEUR '4.00'
                    }
                }
            }

        when:
            ValidatableResponse response = sendPostExchange(PostExchangeRequests.validRequest()
                    .withInputCurrency('PLN')
                    .withInputAmount('10')
                    .withTargetCurrency('PLN'))

        then:
            response.statusCode(200)
                    .body('exchangedInput', Matchers.equalTo('10.00PLN'))
    }

    def "when input and target currencies are different currency then should return exchanged value"() {
        given:
            whenGetLatestWasCalled {
                withBaseCurrencyCode 'PLN'
                respondWith {
                    withCode OK
                    withBody {
                        withBase 'PLN'
                        withUSD '3.50'
                        withEUR '4.00'
                    }
                }
            }

        when:
            ValidatableResponse response = sendPostExchange {
                withInputAmount '10.00'
                withInputCurrency 'PLN'
                withTargetCurrency 'EUR'
            }

        then:
            response.statusCode(200)
                    .body('exchangedInput', Matchers.equalTo('40.00 EUR'))
    }
}
