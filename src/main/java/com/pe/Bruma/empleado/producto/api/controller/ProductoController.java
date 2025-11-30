package com.pe.Bruma.empleado.producto.api.controller;


import com.pe.Bruma.empleado.producto.entity.Producto;
import com.pe.Bruma.empleado.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping()
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Producto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping()
    public Producto registrar(@RequestBody Producto producto) {
        return service.registrar(producto);
    }

    @PutMapping()
    public Producto actualizar(@RequestBody Producto producto) {
        return service.actualizar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/buscar-nombre")
    public List<Producto> buscarPorNombre(@RequestParam String texto) {
        return service.buscarPorNombre(texto);
    }
}