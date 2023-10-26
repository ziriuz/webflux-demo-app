package dev.ziriuz.webflux.demo.mock.controller;

import dev.ziriuz.webflux.demo.mock.dto.EncryptRequest;
import dev.ziriuz.webflux.demo.mock.dto.EncryptResponse;
import dev.ziriuz.webflux.demo.mock.service.EncryptionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@Slf4j
public class EncryptionController {


    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("encrypt")
    public Mono<EncryptResponse> encrypt(@Valid @RequestBody Mono<EncryptRequest> request){

        EncryptResponse response = new EncryptResponse();

        return request.doOnNext(req -> response.setRequestId(req.getRequestId()))
                .flatMap(req -> encryptionService.encrypt(req.getContent()))
                .doOnNext(encryptedContent -> {
                    response.setEncryptedContent(encryptedContent);
                    response.setTimestamp(Instant.now().toEpochMilli());
                })
                .flatMap(encryptedContent -> Mono.just(response));

    }

    @PostMapping("encrypt/stalling")
    public Mono<EncryptResponse> encryptStalling(@Valid @RequestBody Mono<EncryptRequest> request){

        EncryptResponse response = new EncryptResponse();

        return request.doOnNext(req -> response.setRequestId(req.getRequestId()))
                .flatMap(req -> encryptionService.encryptReactorStalling(req.getContent()))
                .doOnNext(encryptedContent -> {
                    response.setEncryptedContent(encryptedContent);
                    response.setTimestamp(Instant.now().toEpochMilli());
                })
                .flatMap(encryptedContent -> Mono.just(response));

    }
}
