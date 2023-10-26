package dev.ziriuz.webflux.demo.mock;

import dev.ziriuz.webflux.demo.mock.dto.EncryptRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class EncryptionControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    private EncryptRequest request;

    @BeforeEach
    public void setup(){
        request = new EncryptRequest(UUID.randomUUID().toString(), Instant.now().toEpochMilli(),"test");
    }

    @Test
    public void doesEncryptionControllerReturnSuccessResponse() {

        webTestClient.post()
                .uri("/encrypt")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    public void doesEncryptionControllerReturnBadRequestWhenMissingMandatoryAttributes() {

        request.setRequestId(null);
        webTestClient.post()
                .uri("/encrypt")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Disabled
    public void demoTestOfStallingReactor() {
        webTestClient.post()
                .uri("/encrypt/stalling")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}
