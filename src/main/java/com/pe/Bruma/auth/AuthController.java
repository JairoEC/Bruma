package com.pe.Bruma.auth;

import com.pe.Bruma.auth.service.AuthenticationService; // <--- IMPORTANTE: Importa tu servicio de Auth
import com.pe.Bruma.usuario.api.request.UsuarioCreateRequestDto;
import com.pe.Bruma.usuario.api.response.UsuarioResponseDto;
import com.pe.Bruma.usuario.service.UsuarioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    // 1. ¡AQUÍ ESTABA EL ERROR! Faltaba inyectar este servicio
    private final AuthenticationService authenticationService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDto> registrar(@RequestBody UsuarioCreateRequestDto request) {
        UsuarioResponseDto nuevoUsuario = usuarioService.createEmpleado(request);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequestDto loginRequest) {

        // Construimos el objeto que pide el servicio usando los datos del DTO
        AuthenticationRequest authRequest = AuthenticationRequest.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();

        // 2. CORREGIDO: Ahora usamos la variable 'authenticationService' que declaramos arriba
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    // DTOs auxiliares para recibir los datos del JSON
    @Data
    public static class LoginRequestDto {
        private String email;
        private String password;
    }
}