package dev.ziriuz.webflux.demo.domain.port.input;

import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;

public interface CustomerService {
    CustomerDetails getCustomerDetails(String id);
}
