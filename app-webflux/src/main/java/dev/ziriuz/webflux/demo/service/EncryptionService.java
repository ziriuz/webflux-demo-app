package dev.ziriuz.webflux.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ziriuz.webflux.demo.client.EncryptionServiceClient;
import dev.ziriuz.webflux.demo.dto.EncryptRequest;
import dev.ziriuz.webflux.demo.dto.EncryptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public class EncryptionService {

    @Autowired
    private EncryptionServiceClient encryptionServiceClient;

    @Autowired
    ObjectMapper objectMapper;

    public Mono<String> encrypt(Object content, Class<?> clazz) {

        EncryptRequest request = null;
        try {
            request = new EncryptRequest(
                    UUID.randomUUID().toString(),
                    Instant.now().toEpochMilli(),
                    objectMapper.writerFor(clazz).writeValueAsString(content)
            );
        } catch (JsonProcessingException e) {
            return Mono.error(new Throwable("Error converting object to json: " + content));
        }
        return encryptionServiceClient.encrypt(request)
                .map(EncryptResponse::getEncryptedContent);
    }

}
