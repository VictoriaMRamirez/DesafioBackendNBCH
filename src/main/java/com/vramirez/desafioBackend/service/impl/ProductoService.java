package com.vramirez.desafioBackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vramirez.desafioBackend.exception.InternalServerException;
import com.vramirez.desafioBackend.exception.ResponseStatusException;
import com.vramirez.desafioBackend.model.CrearProducto;
import com.vramirez.desafioBackend.model.Producto;
import com.vramirez.desafioBackend.repository.IProductoRepository;
import com.vramirez.desafioBackend.service.IProductoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;
    private final ObjectMapper mapper;

    @Autowired
    public ProductoService(IProductoRepository productoRepository, ObjectMapper mapper) {
        this.productoRepository = productoRepository;
        this.mapper = mapper;
    }

    @Override
    public Producto crear(CrearProducto crearProducto) throws InternalServerException {
        Producto producto = null;

        if (crearProducto.getNombre().equals("") || crearProducto.getNombre() == null) {
            log.error("Error en la invocacion de crear productos - El campo de nombre está vacío o nulo");
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "El campo de nombre está vacío o nulo");
        }

        if (crearProducto.getPrecio() == 0) {
            log.error("Error en la invocacion de crear productos - El campo de precio está vacío");
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "El campo de precio está vacío");
        }

        try {
            producto = mapper.convertValue(crearProducto, Producto.class);
            productoRepository.save(producto);

            if (crearProducto.getDescripcion().equals("") || crearProducto.getDescripcion() == null) {
                productoRepository.save(producto);
            }

            log.info("El producto " + producto.getNombre() + " ID " + producto.getIdProducto() + " ha sido creado");

        } catch (InternalServerException e) {
            throw new InternalServerException(e.getStatusCode(), e.message, e.statusText);
        }

        return producto;
    }

    @Override
    public List<Producto> listarTodos() throws InternalServerException {
        List<Producto> productos = new ArrayList<>();

        if (productoRepository.findAll().isEmpty()) {
            log.error("Error en la invocacion de listar productos - La lista de productos esta vacía");
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "La lista de productos esta vacía");
        }

        try {
            productos = productoRepository.findAll();
            log.info("Existe una lista de productos");

        } catch (InternalServerException e) {
            throw new InternalServerException(e.getStatusCode(), e.message, e.statusText);
        }

        return productos;
    }

    @Override
    public Optional<Producto> buscarPorId(Integer idProducto) throws ResponseStatusException, InternalServerException {
        Optional<Producto> producto = null;

        if (!productoRepository.existsById(idProducto)) {
            log.error("Error en la invocacion de buscar producto por ID - No se ha encontrado el producto con ID " + idProducto);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto no existe" ,"No se ha encontrado el producto con ID " + idProducto);
        }

        try {
            producto = productoRepository.findById(idProducto);
            log.info("Producto ID " + idProducto + " encontrado");

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.statusCode, e.message, e.statusText);
        } catch (InternalServerException e) {
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "Error interno del servidor");
        }

        return producto;
    }

    @Override
    public void eliminarPorId(Integer idProducto) throws ResponseStatusException, InternalServerException {

        if (!productoRepository.existsById(idProducto)) {
            log.error("Error en la invocacion de buscar producto por ID - No se ha encontrado el producto con ID " + idProducto);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto no existe" ,"No se ha encontrado el producto con ID " + idProducto);
        }

        try {
            productoRepository.deleteById(idProducto);
            log.info("Producto con ID " + idProducto + " eliminado");

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.statusCode, e.message, e.statusText);
        } catch (InternalServerException e) {
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", "Error interno del servidor");
        }
    }
}
