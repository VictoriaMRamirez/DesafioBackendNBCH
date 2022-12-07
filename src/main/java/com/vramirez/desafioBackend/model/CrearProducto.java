package com.vramirez.desafioBackend.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CrearProducto {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 5000)
    private String descripcion;

    @NotBlank
    private float precio;
}
