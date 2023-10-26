package dev.ziriuz.webflux.demo.controller;

import dev.ziriuz.webflux.demo.domain.model.ContactDetails;
import dev.ziriuz.webflux.demo.domain.port.input.CustomerService;
import dev.ziriuz.webflux.demo.dto.*;
import dev.ziriuz.webflux.demo.service.EncryptionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.time.Instant;

@RestController
@Slf4j
public class CustomerDetailsController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/customer")
    public Mono<CustomerDetailsResponse> getCustomerDetails(@Valid @RequestBody Mono<CustomerDetailsRequest> request) {

        return request.map(CustomerRequestContext::new)
                .flatMap(this::addCustomerDetailsToContext)
                .flatMap(this::encryptSensitiveData)
                .map(this::buildCustomerDetailsResponse);

    }

    private Mono<CustomerRequestContext> addCustomerDetailsToContext(CustomerRequestContext context) {
        return customerService.getCustomerDetails(context.getRequest().getCustomerId())
                .doOnNext(context::setCustomerDetails)
                .thenReturn(context);
    }

    private Mono<CustomerRequestContext> encryptSensitiveData(CustomerRequestContext context) {
        return encryptionService.encrypt(context.getCustomerDetails().getContactDetails(), ContactDetails.class)
                .doOnNext(context::setEncryptedContent)
                .thenReturn(context);
    }
    private CustomerDetailsResponse buildCustomerDetailsResponse (CustomerRequestContext context) {

        CustomerDetailsResponse response = new CustomerDetailsResponse();
        response.setRequestId(context.getRequest().getRequestId());
        response.setCustomerId(context.getCustomerDetails().getCustomerId());
        response.setName(context.getCustomerDetails().getName());
        response.setEncryptedContent(context.getEncryptedContent());
        response.setTimestamp(Instant.now().toEpochMilli());

        return response;
    }
}
