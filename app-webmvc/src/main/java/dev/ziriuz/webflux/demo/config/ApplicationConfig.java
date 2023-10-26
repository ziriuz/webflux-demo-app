package dev.ziriuz.webflux.demo.config;

import dev.ziriuz.webflux.demo.domain.port.input.CustomerService;
import dev.ziriuz.webflux.demo.domain.port.output.CustomerRepository;
import dev.ziriuz.webflux.demo.domain.service.CustomerDomainService;
import dev.ziriuz.webflux.demo.repository.CustomerFakerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CustomerRepository customerRepository() {
        return new CustomerFakerRepository();
    }

    @Bean
    CustomerService customerService(CustomerRepository customerRepository) {
        return new CustomerDomainService(customerRepository);
    }

}
