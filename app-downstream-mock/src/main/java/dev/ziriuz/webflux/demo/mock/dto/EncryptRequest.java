package dev.ziriuz.webflux.demo.mock.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EncryptRequest {
    @NotNull(message = "requestId cannot be null")
    private String requestId;
    private long timestamp; //unix epoch millis
    @NotNull(message = "content cannot be null")
    private String content;
}
