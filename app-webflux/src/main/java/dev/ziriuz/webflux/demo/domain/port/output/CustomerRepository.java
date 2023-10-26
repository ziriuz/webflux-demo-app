package dev.ziriuz.webflux.demo.domain.port.output;

import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<CustomerDetails> getCustomerDetailsById(String id);
}
