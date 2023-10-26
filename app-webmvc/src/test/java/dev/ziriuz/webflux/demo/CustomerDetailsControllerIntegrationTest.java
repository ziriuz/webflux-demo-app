package dev.ziriuz.webflux.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ziriuz.webflux.demo.client.EncryptionServiceClient;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsRequest;
import dev.ziriuz.webflux.demo.dto.EncryptResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerDetailsControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
                .thenReturn(mockedEncryptionResponse);
    }

    @Test
    public void doesActionControllerReturnSuccessResponse() throws Exception {
        this.mockMvc.perform(
                    get("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.encryptedContent").value(ENCRYPTED_CONTENT))
                .andReturn();
    }
}
