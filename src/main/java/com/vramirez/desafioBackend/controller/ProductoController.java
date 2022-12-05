package com.vramirez.desafioBackend.controller;

import com.vramirez.desafioBackend.exception.GlobalHandlerException;
import com.vramirez.desafioBackend.model.CrearProducto;
import com.vramirez.desafioBackend.model.Producto;
import com.vramirez.desafioBackend.service.impl.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Tag(name = "Productos", description = "API para gestionar productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/productos")
    @Operation(description = "Crear un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    public ResponseEntity<?> crearProducto(@RequestBody CrearProducto crearProducto) {
        return null;
    }

    @GetMapping("/productos")
    @Operation(description = "Retorna la lista de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    public ResponseEntity<?> listarProductos(@RequestBody Producto Producto){
        return null;
    }


    @GetMapping("/productos/{idProducto}")
    @Operation(description = "Retorna un producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "404", description = "El producto no existe"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Integer idProducto){
        return null;
    }

    @DeleteMapping("/delete/{idProducto}")
    @Operation(description = "Eliminar un producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "404", description = "El producto no existe"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Integer idProducto){
        return null;
    }
}
