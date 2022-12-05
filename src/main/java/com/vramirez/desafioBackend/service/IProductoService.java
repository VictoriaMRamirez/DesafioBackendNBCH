package com.vramirez.desafioBackend.service;

import com.vramirez.desafioBackend.exception.GlobalHandlerException;
import com.vramirez.desafioBackend.model.CrearProducto;
import com.vramirez.desafioBackend.model.Producto;

import java.util.List;

public interface IProductoService {
    void crear(CrearProducto crearProducto);
    List<Producto> listarTodos();
    Producto buscarPorId(Integer idProducto);
    void eliminarPorId(Integer idProducto);
}
