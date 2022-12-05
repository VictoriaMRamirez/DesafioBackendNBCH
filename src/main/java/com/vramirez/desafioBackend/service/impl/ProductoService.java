package com.vramirez.desafioBackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vramirez.desafioBackend.model.CrearProducto;
import com.vramirez.desafioBackend.model.Producto;
import com.vramirez.desafioBackend.repository.IProductoRepository;
import com.vramirez.desafioBackend.service.IProductoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    private final static Logger logger = Logger.getLogger(ProductoService.class);

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void crear(CrearProducto crearProducto) {

    }

    @Override
    public List<Producto> listarTodos() {
        return null;
    }

    @Override
    public Producto buscarPorId(Integer idProducto) {
        return null;
    }

    @Override
    public void eliminarPorId(Integer idProducto) {

    }
}
