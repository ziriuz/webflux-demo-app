package dev.ziriuz.webflux.demo.domain.model;

import lombok.Data;

@Data
public class CustomerDetails {
    private String customerId;
    private String name;
    private ContactDetails contactDetails;
}
