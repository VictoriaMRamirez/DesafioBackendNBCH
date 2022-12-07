package com.vramirez.desafioBackend.controller;

import com.vramirez.desafioBackend.exception.InternalServerException;
import com.vramirez.desafioBackend.exception.ResponseStatusException;
import com.vramirez.desafioBackend.model.CrearProducto;
import com.vramirez.desafioBackend.model.ErrorGenerico;
import com.vramirez.desafioBackend.model.ErrorNoEncontrado;
import com.vramirez.desafioBackend.model.Producto;
import com.vramirez.desafioBackend.service.impl.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Productos", description = "API para gestionar productos")
@Log4j2
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/productos")
    @Operation(description = "Crear un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta exitosa", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenerico.class))
            })
    })
    public ResponseEntity<Producto> crearProducto(@RequestBody CrearProducto crearProducto) throws InternalServerException {
        Producto producto = productoService.crear(crearProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    @GetMapping("/productos")
    @Operation(description = "Retorna la lista de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenerico.class))
            })
    })
    public ResponseEntity<List<Producto>> listarProductos() throws InternalServerException {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(productos);
    }

    @GetMapping("/productos/{idProducto}")
    @Operation(description = "Retorna un producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorNoEncontrado.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenerico.class))
            })
    })
    public ResponseEntity<Optional<Producto>> buscarProductoPorId(@PathVariable Integer idProducto) throws ResponseStatusException, InternalServerException {
        Optional<Producto> producto = productoService.buscarPorId(idProducto);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @DeleteMapping("/delete/{idProducto}")
    @Operation(description = "Eliminar un producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Respuesta exitosa", content = {
                    @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorNoEncontrado.class))}),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenerico.class))
            })
    })
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Integer idProducto) throws ResponseStatusException, InternalServerException {
        productoService.eliminarPorId(idProducto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Producto con ID " + idProducto + " eliminado");
    }
}
