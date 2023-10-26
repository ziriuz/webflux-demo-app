package dev.ziriuz.webflux.demo.mock.config;

import dev.ziriuz.webflux.demo.mock.service.EncryptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EncryptionService actionService(){
        return new EncryptionService();
    }

}
