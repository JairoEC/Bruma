package com.pe.Bruma.mesa.api.controller;

import com.pe.Bruma.mesa.api.request.MesaCreateRequestDto;
import com.pe.Bruma.mesa.api.request.MesaUpdateRequestDto;
import com.pe.Bruma.mesa.api.response.MesaResponseDto;
import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.service.MesaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<MesaResponseDto>> listar() {
        return ResponseEntity.ok(mesaService.getAllMesa());
    }

    @PostMapping
    public ResponseEntity<MesaResponseDto> crear(@Valid @RequestBody MesaCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.createMesa(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaResponseDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MesaUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(mesaService.updateMesa(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mesaService.deleteMesa(id);
        return ResponseEntity.noContent().build();
    }
}
