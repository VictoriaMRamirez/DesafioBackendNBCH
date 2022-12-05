package com.vramirez.desafioBackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Schema
public class ErrorNoEncontrado {

    @NotBlank
    private String codigo;

    private String mensaje;
}
