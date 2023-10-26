package dev.ziriuz.webflux.demo.mock;

import dev.ziriuz.webflux.demo.mock.service.EncryptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EncryptionServiceIntegrationTest {

    @Autowired
    EncryptionService service;


    @Test
    public void doesEncryptionServiceEncryptContent() {
        var mono = Mono.just("Content").subscribeOn(Schedulers.parallel())
                .flatMap(content -> service.encrypt(content));

        StepVerifier.create(mono)
                .consumeNextWith(s ->
                        Assertions.assertEquals("Content",service.decode(s)))
                .verifyComplete();
    }

    @Test
    @Disabled
    public void demoTestOfStallingReactor() {
        var mono = Mono.just("Content").subscribeOn(Schedulers.parallel())
                        .flatMap(content -> service.encryptReactorStalling(content));

        StepVerifier.create(mono)
                .consumeNextWith(s ->
                        Assertions.assertEquals("Content",service.decode(s)))
                .verifyComplete();
    }
}
