package com.example.feign

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "integration")
class BaseIntegrationSpec extends Specification {
    @Value("\${fake-external-service.port}")
    private int fakeWireMockPort

    @LocalServerPort
    private int localServerPort

    @Autowired
    @Rule
    WireMockRuleBean wireMockRule

    WireMockRule wireMockRule() {
        wireMockRule
    }
}
