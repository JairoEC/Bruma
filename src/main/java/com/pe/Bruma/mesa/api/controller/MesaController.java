package com.pe.Bruma.mesa.api.controller;

import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.service.MesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public List<Mesa> listar() {
        return mesaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Mesa> crear(@RequestBody Mesa mesa) {
        Mesa nuevaMesa = mesaService.crear(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> actualizar(@PathVariable Long id,
                                           @RequestBody Mesa datosMesa) {
        datosMesa.setId(id);
        Mesa actualizada = mesaService.actualizar(datosMesa);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
