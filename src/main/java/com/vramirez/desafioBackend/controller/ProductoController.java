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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Productos", description = "API para gestionar productos")
public class ProductoController {

    private final static Logger logger = Logger.getLogger(ProductoController.class);

    @Autowired
    ProductoService productoService;

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
        Producto producto = null;

        try {
            producto = productoService.crear(crearProducto);

            logger.info("El producto " + producto.getNombre() + " ID " + producto.getIdProducto() + " ha sido creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);

        } catch (InternalServerException e) {
            logger.error("Error en la invocacion de crear productos - " + e.statusText);
            throw new InternalServerException(e.getStatusCode(), e.message, e.statusText);
        }
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
        List<Producto> productos = null;

        try {
            productos = productoService.listarTodos();

            logger.info("Existe una lista de productos");
            return ResponseEntity.status(HttpStatus.OK).body(productos);

        } catch (InternalServerException e) {
            logger.error("Error en la invocacion de listar productos - " + e.statusText);
            throw new InternalServerException(e.getStatusCode(), e.message, e.statusText);
        }
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
        Optional<Producto> producto = null;

        try {
            producto = productoService.buscarPorId(idProducto);
            logger.info("Producto ID " + idProducto + "encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(producto);

        } catch (ResponseStatusException e) {
            logger.error("Error en la invocacion de buscar producto por ID - " + e.statusText);
            throw new ResponseStatusException(e.statusCode, e.message, e.statusText);

        } catch (InternalServerException e) {
            logger.error("Error en la invocacion de buscar producto por ID - " + e.statusText);
            throw new InternalServerException(e.getStatusCode(), e.message, e.statusText);
        }
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

        try {
            productoService.eliminarPorId(idProducto);
            logger.info("Producto con ID " + idProducto + " eliminado");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Producto con ID " + idProducto + " eliminado");

        } catch (ResponseStatusException e) {
            logger.error("Error en la invocacion de buscar producto por ID - " + e.statusText);
            throw new ResponseStatusException(e.statusCode, e.message, e.statusText);

        } catch (InternalServerException e) {
            logger.error("Error en la invocacion de buscar producto por ID - " + e.statusText);
            throw new InternalServerException(e.getStatusCode(), e.message, e.statusText);
        }
    }
}
