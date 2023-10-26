package dev.ziriuz.webflux.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ContactDetails {
    private String email;
    private String telephone;
    private String address;
}
