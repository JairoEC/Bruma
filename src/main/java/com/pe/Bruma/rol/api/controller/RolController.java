package com.pe.Bruma.rol.api.controller;

import com.pe.Bruma.rol.api.request.RolCreateRequestDto;
import com.pe.Bruma.rol.api.request.RolUpdateRequestDto;
import com.pe.Bruma.rol.api.response.RolResponseDto;
import com.pe.Bruma.rol.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    //CREAR
    @PostMapping
    public ResponseEntity<RolResponseDto> crear(@Valid @RequestBody RolCreateRequestDto dto){

        RolResponseDto rolGuardado = rolService.createRol(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolGuardado);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDto> actualizar(@PathVariable Long id,
                                                     @Valid @RequestBody RolUpdateRequestDto dto){
        RolResponseDto rolActualizado = rolService.updateRol(id, dto);
        return ResponseEntity.ok(rolActualizado);
    }

    // LISTAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> obtenerPorId(@PathVariable Long id) {
        RolResponseDto rol = rolService.getRol(id);
        return ResponseEntity.ok(rol);
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<RolResponseDto>> listarTodos() {
        List<RolResponseDto> roles = rolService.getAllRol();
        return ResponseEntity.ok(roles);
    }
    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }

}
