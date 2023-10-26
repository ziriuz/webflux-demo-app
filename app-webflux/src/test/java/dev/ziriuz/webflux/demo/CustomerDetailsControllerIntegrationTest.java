package dev.ziriuz.webflux.demo;

import dev.ziriuz.webflux.demo.client.EncryptionServiceClient;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsRequest;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsResponse;
import dev.ziriuz.webflux.demo.dto.EncryptResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CustomerDetailsControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EncryptionServiceClient encryptionServiceClient;

    private CustomerDetailsRequest request;
    private EncryptResponse mockedEncryptionResponse;

    private final static String ENCRYPTED_CONTENT = "ENCRYPTED_CONTENT";

    @BeforeEach
    public void setup()
    {
        request = new CustomerDetailsRequest("TEST_REQUEST_ID", Instant.now().toEpochMilli(), "TEST_CUSTOMER_ID");
        mockedEncryptionResponse = new EncryptResponse("TEST_REQUEST_ID", Instant.now().toEpochMilli(), ENCRYPTED_CONTENT);

        Mockito.when(encryptionServiceClient.encrypt(Mockito.any()))
                .thenReturn(Mono.just(mockedEncryptionResponse));
    }

    @Test
    public void doesActionControllerReturnSuccessResponse() {

        webTestClient.method(HttpMethod.GET)
                .uri("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.encryptedContent").isEqualTo(ENCRYPTED_CONTENT);
    }

    @Test
    public void doesActionControllerReturnSuccessResponseStepVerifierExample() {

        var responseFlux = webTestClient.method(HttpMethod.GET)
                .uri("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(CustomerDetailsResponse.class)
                .getResponseBody()
                ;

        StepVerifier.create(responseFlux)
                .consumeNextWith(response -> {
                    System.out.println("==============================");
                    System.out.println(response);
                    System.out.println("==============================");
                    Assertions.assertEquals(ENCRYPTED_CONTENT, response.getEncryptedContent());
                }
                )
                .verifyComplete();
    }
}
