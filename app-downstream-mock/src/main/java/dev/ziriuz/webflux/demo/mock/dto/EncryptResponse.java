package dev.ziriuz.webflux.demo.mock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EncryptResponse {
    private String requestId;
    private long timestamp; //unix epoch millis
    private String encryptedContent;
}
