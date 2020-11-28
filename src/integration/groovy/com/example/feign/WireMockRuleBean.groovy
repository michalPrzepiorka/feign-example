package com.example.feign

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class WireMockRuleBean extends WireMockRule {
    WireMockRuleBean(@Value("\${fake-external-service.port}") int port) {
        super(port)
    }
}
