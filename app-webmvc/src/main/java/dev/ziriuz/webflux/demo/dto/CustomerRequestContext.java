package dev.ziriuz.webflux.demo.dto;

import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerRequestContext {
    private CustomerDetailsRequest request;
    private CustomerDetails customerDetails;
    private String encryptedContent;

    public CustomerRequestContext(CustomerDetailsRequest request) {
        this.request = request;
    }
}
