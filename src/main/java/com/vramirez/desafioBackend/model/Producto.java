package com.vramirez.desafioBackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Schema
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class Producto /*implements Serializable*/ {

    /*@Serial
    private static final Integer serialVersionUID = 1;*/

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer idProducto;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 5000)
    @Column(name = "descripcion", length = 5000)
    private String descripcion;

    @NotBlank
    @Column(name = "precio", nullable = false)
    private float precio;

    @NotBlank
    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonDeserialize(using = StringDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime fechaCreacion;
}
