package com.pe.Bruma.empleado.api.controller;

import com.pe.Bruma.empleado.api.request.EmpleadoCreateRequestDto;
import com.pe.Bruma.empleado.api.request.EmpleadoUpdateRequestDto;
import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;
import com.pe.Bruma.empleado.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    // CREAR
    @PostMapping
    public ResponseEntity<EmpleadoResponseDto> create(@Valid @RequestBody EmpleadoCreateRequestDto dto) {
        EmpleadoResponseDto nuevoEmpleado = empleadoService.createEmpleado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }


    //LISTAR
    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDto>> getAll() {
        List<EmpleadoResponseDto> empleados = empleadoService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    //OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDto> getById(@PathVariable Long id) {
        EmpleadoResponseDto empleado = empleadoService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);

    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody EmpleadoUpdateRequestDto dto) {
        EmpleadoResponseDto empleadoActualizado = empleadoService.updateEmpleado(id, dto);
        return ResponseEntity.ok(empleadoActualizado);
    }
}
