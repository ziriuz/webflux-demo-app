package dev.ziriuz.webflux.demo.domain.service;


import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import dev.ziriuz.webflux.demo.domain.port.input.CustomerService;
import dev.ziriuz.webflux.demo.domain.port.output.CustomerRepository;

public class CustomerDomainService implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDomainService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDetails getCustomerDetails(String id) {
        return customerRepository.getCustomerDetailsById(id);
    }
}
