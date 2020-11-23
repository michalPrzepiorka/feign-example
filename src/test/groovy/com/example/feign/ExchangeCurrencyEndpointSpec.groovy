package com.example.feign

import com.example.feign.domain.ExchangeRatesProvider
import com.example.feign.domain.Exchanger
import com.example.feign.domain.Money
import com.example.feign.infrastructure.out.exchangeapi.CurrencyClient
import com.example.feign.infrastructure.out.exchangeapi.CurrencyClientFactory
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import spock.lang.Specification
import spock.lang.Subject

import static com.github.tomakehurst.wiremock.client.WireMock.*

class ExchangeCurrencyEndpointSpec extends Specification {

    private static final int PORT = 8080
    private static final String HOST = "localhost"

    @Rule
    WireMockRule wireMockServer = new WireMockRule()

    @Subject
    CurrencyClient application


    void setup() {
        this.application = new CurrencyClientFactory().createClient(URI.create(wireMockServer.baseUrl()))
        wireMockServer.start()

        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
        mockResponse.withStatus(200)

//        WireMock.configureFor(HOST, PORT)
//        WireMock.stubFor(
//                WireMock.post("/exchange")
//                        .willReturn(mockResponse)
//        )
    }

    def "should exchange currency to the set value"() {
        given:
        ExchangeRatesProvider dummy = new DummyExchangeRates();
        def exchanger = new Exchanger(dummy)
        stubFor(get(urlEqualTo('/latest?base=PLN'))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(
                                exchanger.exchange(
                                        new Money(BigDecimal.valueOf(9), Currency.getInstance("PLN")),
                                        Currency.getInstance("EUR")
                                ).getAmount().toString()
                        )
                )
        )

        when:
        application

        then:
        verify(getRequestedFor(urlEqualTo('/latest?base=PLN'))
        )
    }
}
