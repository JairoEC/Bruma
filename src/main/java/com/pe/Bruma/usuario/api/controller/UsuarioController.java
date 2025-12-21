package com.pe.Bruma.usuario.api.controller;

import com.pe.Bruma.security.service.Impl.UserDetailServiceImpl;
import com.pe.Bruma.usuario.api.request.UsuarioCreateRequestDto;
import com.pe.Bruma.usuario.api.request.UsuarioUpdateRequestDto;
import com.pe.Bruma.usuario.api.response.UsuarioResponseDto;
import com.pe.Bruma.usuario.entity.Usuario;
import com.pe.Bruma.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // CREAR
    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateRequestDto dto) {
        UsuarioResponseDto nuevoEmpleado = usuarioService.createEmpleado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }


    //LISTAR
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<UsuarioResponseDto> empleados = usuarioService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    //OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        UsuarioResponseDto empleado = usuarioService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);

    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody UsuarioUpdateRequestDto dto) {
        UsuarioResponseDto empleadoActualizado = usuarioService.updateEmpleado(id, dto);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @PostMapping("/auth")
    public ResponseEntity<UsuarioResponseDto> getUsuario(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UsuarioResponseDto usuarioDto = usuarioService.findByDni(username);
        return ResponseEntity.ok(usuarioDto);
    }
}
