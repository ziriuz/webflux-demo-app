package dev.ziriuz.webflux.demo.mock.service;

import dev.ziriuz.webflux.demo.mock.util.Utils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

public class EncryptionService {

    public String encode(String content) {
        return new String(Base64.getEncoder().encode(content.getBytes(StandardCharsets.UTF_8)));
    }

    public String decode(String content) {
        return new String(Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8)));
    }

    public Mono<String> encrypt(String content) {
        return Mono.delay(Duration.ofSeconds(3))
                .then(Mono.fromSupplier(() -> encode(content))
        );
    }

    /**
     *  Blocking method to demonstrate freezing of event Loop
     */
    private String encodeDelayed(String content) {
        Utils.sleepSeconds(3);
        return encode(content);
    }
    /**
     *  Imposter method which is doing blocking operation before consumer subscribes
     *  It is BAD code to demonstrate freezing of Event loop
     *  Enable and run EncryptionServiceIntegrationTest.demoTestOfStallingReactor
     *  to see how BlockHound can help to detect blocking operations in Event loop
     */
    public Mono<String> encryptReactorStalling(String content) {
        return Mono.just(encodeDelayed(content));
    }

    /**
     *  Proper implementation of blocking call from reactive method
     *  Hiding knowledge of blocking from caller
     *  Blocking on proper scheduler without requiring the caller to do so
     *  Not putting blocking logic before consumer subscribes
     */
    public Mono<String> encryptBlockingEncapsulation(String content) {
        return Mono.fromSupplier(() -> encodeDelayed(content))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
