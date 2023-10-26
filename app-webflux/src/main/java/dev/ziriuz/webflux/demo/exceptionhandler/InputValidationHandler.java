package dev.ziriuz.webflux.demo.exceptionhandler;

import dev.ziriuz.webflux.demo.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
public class InputValidationHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleWebExchangeBindException(WebExchangeBindException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "ERR-INVALID-ATTRIBUTES",
                ex.getMessage()
        ));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorResponse> handleServerWebInputException(ServerWebInputException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "ERR-INVALID-REQUEST",
                ex.getMessage()
        ));
    }
}
