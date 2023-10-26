package dev.ziriuz.webflux.demo.exceptionhandler;

import dev.ziriuz.webflux.demo.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@ControllerAdvice
public class DownstreamErrorHandler {
    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ErrorResponse> handleWebClientRequestException(WebClientRequestException ex){
        return ResponseEntity.status(503)
                .body(new ErrorResponse("ERR-DOWNSTREAM-NOT-AVAILABLE",ex.getMessage()));
    }

}
