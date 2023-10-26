package dev.ziriuz.webflux.demo.client;

import dev.ziriuz.webflux.demo.dto.EncryptRequest;
import dev.ziriuz.webflux.demo.dto.EncryptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Service
public class EncryptionServiceClient {

    private final WebClient webClient;

    public EncryptionServiceClient(@Value("${downstream.service.url}") String url){

        ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(HttpClient.create(
                ConnectionProvider.builder("downstreamClientConnectionPool")
                        .maxConnections(1500)
                        .pendingAcquireMaxCount(10000)
                        .build()
        ));

        this.webClient = WebClient.builder()
                .baseUrl(url)
                .clientConnector(clientHttpConnector)
                .build();
    }

    public Mono<EncryptResponse> encrypt(EncryptRequest requestDto) {
        return webClient.post()
                .uri("/encrypt")
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(EncryptResponse.class);
    }

}