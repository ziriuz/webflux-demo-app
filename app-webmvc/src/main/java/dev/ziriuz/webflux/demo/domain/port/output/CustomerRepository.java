package dev.ziriuz.webflux.demo.domain.port.output;

import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;

public interface CustomerRepository {
    CustomerDetails getCustomerDetailsById(String id);
}
