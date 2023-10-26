package dev.ziriuz.webflux.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;


/**
 * Web Flux custom configuration
 */
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    // Overwrite methods of WebFluxConfigurer to change default configuration
}