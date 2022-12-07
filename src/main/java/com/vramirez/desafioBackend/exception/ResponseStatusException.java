package com.vramirez.desafioBackend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseStatusException extends NullPointerException {
    public HttpStatus statusCode;
    public String statusText;
    public String message;

    public ResponseStatusException(HttpStatus statusCode, String message, String statusText) {
        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.message = message;
    }
}
