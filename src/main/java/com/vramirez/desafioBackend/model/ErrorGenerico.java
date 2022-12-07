package com.vramirez.desafioBackend.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ErrorGenerico {

    @NotBlank
    private String codigo;

    private String mensaje;
}
