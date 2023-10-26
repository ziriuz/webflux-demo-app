package dev.ziriuz.webflux.demo.domain.port.input;

import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<CustomerDetails> getCustomerDetails(String id);
}
