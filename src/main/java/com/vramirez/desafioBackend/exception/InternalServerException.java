package com.vramirez.desafioBackend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class InternalServerException extends RuntimeException {
    private HttpStatus statusCode;
    public String statusText;
    public String message;

    public InternalServerException(HttpStatus statusCode, String message, String statusText) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.statusText = statusText;
    }
}
