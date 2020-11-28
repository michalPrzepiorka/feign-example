package com.example.feign

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.junit.Before
import org.springframework.boot.web.server.LocalServerPort

trait SendRequestToServerAbility {
    @LocalServerPort
    private int localServerPort

    @Before
    void beforeSendRequestToServerAbility() {
        RestAssured.baseURI = "http://localhost:${localServerPort}"
    }

    ValidatableResponse sendPostExchange(PostExchangeRequest request) {
        RestAssured.given()
                .body(request)
                .contentType(ContentType.JSON)
                .post('/exchange')
                .then()
    }

    // Nieco inny sposób przekazania obiektu żądania
    ValidatableResponse sendPostExchange(@DelegatesTo(PostExchangeRequest) Closure<PostExchangeRequest> closure) {
        PostExchangeRequest request = PostExchangeRequests.validRequest().with(closure)

        RestAssured.given()
                .body(request)
                .contentType(ContentType.JSON)
                .post('/exchange')
                .then()
    }
}

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class PostExchangeRequest {
    String inputAmount;
    String inputCurrency;
    String targetCurrency;
}

class PostExchangeRequests {
    static PostExchangeRequest validRequest() {
        new PostExchangeRequest()
                .withInputAmount('2.00')
                .withInputCurrency('PLN')
                .withTargetCurrency('EUR')
    }
}
