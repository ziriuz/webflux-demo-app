package dev.ziriuz.webflux.demo.controller;

import dev.ziriuz.webflux.demo.domain.model.ContactDetails;
import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import dev.ziriuz.webflux.demo.domain.port.input.CustomerService;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsRequest;
import dev.ziriuz.webflux.demo.dto.CustomerDetailsResponse;
import dev.ziriuz.webflux.demo.service.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@Slf4j
public class CustomerDetailsController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/customer")
    public CustomerDetailsResponse getCustomerDetails(@RequestBody CustomerDetailsRequest request) {

        CustomerDetails customerDetails = customerService.getCustomerDetails(request.getCustomerId());
        String encryptedContent = encryptionService.encrypt(customerDetails.getContactDetails(), ContactDetails.class);
        return buildCustomerDetailsResponse(request.getRequestId(), customerDetails, encryptedContent);

    }

    private CustomerDetailsResponse buildCustomerDetailsResponse (
            String requestId, CustomerDetails customerDetails, String encryptedContent
    ) {
        CustomerDetailsResponse response = new CustomerDetailsResponse();
        response.setRequestId(requestId);
        response.setCustomerId(customerDetails.getCustomerId());
        response.setName(customerDetails.getName());
        response.setEncryptedContent(encryptedContent);
        response.setTimestamp(Instant.now().toEpochMilli());
        return response;
    }
}
