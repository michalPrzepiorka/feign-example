package com.example.feign

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import groovy.json.JsonOutput
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import org.springframework.http.HttpStatus

trait ExchangeRatesApiStubAbility {
    abstract WireMockRule wireMockRule()

    void whenGetLatestWasCalled(@DelegatesTo(GetLatestCallConfiguration) Closure<GetLatestCallConfiguration> closure) {
        GetLatestCallConfiguration configuration = GetLatestCallConfigurations.valid().with(closure)

        wireMockRule().stubFor(WireMock.get(WireMock.urlMatching('^/latest.*'))
                .withQueryParam('base', WireMock.equalTo(configuration.baseCurrencyCode))
                .willReturn(WireMock.aResponse()
                        .withStatus(configuration.response.code.value())
                        .withBody(JsonOutput.toJson(configuration.response.body))
                )
        )
    }
}

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class GetLatestCallConfiguration {
    String baseCurrencyCode
    Response response

    GetLatestCallConfiguration respondWith(@DelegatesTo(Response) Closure<Response> closure) {
        response = Response.valid().with(closure)
        this
    }

    @Builder(builderStrategy = SimpleStrategy, prefix = 'with')
    static class Response {
        HttpStatus code
        Body body

        Response withBody(@DelegatesTo(Body) Closure<Body> closure) {
            body = Body.valid().with(closure)
            this
        }

        @Builder(builderStrategy = SimpleStrategy, prefix = 'with')
        static class Body {
            Map<String, BigDecimal> rates;
            String base;

            Body withUSD(String value) {
                rates['USD'] = value.toBigDecimal()
                this
            }

            Body withEUR(String value) {
                rates['EUR'] = value.toBigDecimal()
                this
            }

            static Body valid() {
                new Body()
                        .withRates([:])
                        .withBase('PLN')
            }
        }

        static Response valid() {
            new Response()
                    .withCode(HttpStatus.OK)
                    .withBody(Body.valid())
        }
    }
}

class GetLatestCallConfigurations {
    static GetLatestCallConfiguration valid() {
        new GetLatestCallConfiguration()
                .withBaseCurrencyCode(null)
                .withResponse(GetLatestCallConfiguration.Response.valid())

    }
}
