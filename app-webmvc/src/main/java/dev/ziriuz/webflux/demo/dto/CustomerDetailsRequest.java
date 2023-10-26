package dev.ziriuz.webflux.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDetailsRequest {
    private String requestId;
    private long timestamp;
    private String customerId;
}
