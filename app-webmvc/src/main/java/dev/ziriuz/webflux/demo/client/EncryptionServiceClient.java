package dev.ziriuz.webflux.demo.client;

import dev.ziriuz.webflux.demo.dto.EncryptRequest;
import dev.ziriuz.webflux.demo.dto.EncryptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EncryptionServiceClient {


    private final RestTemplate restTemplate = new RestTemplate();
    private final String downstreamBaseUrl;

    public EncryptionServiceClient(@Value("${downstream.service.url}") String url){
        this.downstreamBaseUrl = url;
    }

    public EncryptResponse encrypt(EncryptRequest requestDto) {
        HttpEntity<EncryptRequest> request = new HttpEntity<>(requestDto);
        return restTemplate.postForObject(downstreamBaseUrl + "/encrypt", request, EncryptResponse.class);
    }

}