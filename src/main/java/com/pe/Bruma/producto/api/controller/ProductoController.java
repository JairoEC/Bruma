package com.pe.Bruma.producto.api.controller;


import com.pe.Bruma.producto.entity.Producto;
import com.pe.Bruma.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/buscar/{id}")
    public Producto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public Producto registrar(@RequestBody Producto producto) {
        return service.registrar(producto);
    }

    @PutMapping("/actualizar")
    public Producto actualizar(@RequestBody Producto producto) {
        return service.actualizar(producto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/buscar-nombre")
    public List<Producto> buscarPorNombre(@RequestParam String texto) {
        return service.buscarPorNombre(texto);
    }
}