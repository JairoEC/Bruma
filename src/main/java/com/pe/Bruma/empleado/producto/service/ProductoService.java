package com.pe.Bruma.empleado.producto.service;

import com.pe.Bruma.empleado.producto.entity.Producto;
import com.pe.Bruma.empleado.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Producto registrar(Producto producto) {
        return repo.save(producto);
    }

    public Producto actualizar(Producto producto) {
        return repo.save(producto);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public List<Producto> buscarPorNombre(String texto) {
        return repo.buscarPorNombre(texto);
    }
}
