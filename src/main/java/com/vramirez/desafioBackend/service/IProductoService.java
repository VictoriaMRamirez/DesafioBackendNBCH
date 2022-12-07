package com.vramirez.desafioBackend.service;

import com.vramirez.desafioBackend.exception.InternalServerException;
import com.vramirez.desafioBackend.exception.ResponseStatusException;
import com.vramirez.desafioBackend.model.CrearProducto;
import com.vramirez.desafioBackend.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Producto crear(CrearProducto crearProducto) throws InternalServerException;
    List<Producto> listarTodos() throws InternalServerException;
    Optional<Producto> buscarPorId(Integer idProducto) throws ResponseStatusException, InternalServerException;
    void eliminarPorId(Integer idProducto) throws ResponseStatusException, InternalServerException;
}
