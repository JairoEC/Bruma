package com.pe.Bruma.auth.service;

import com.pe.Bruma.auth.AuthenticationRequest;
import com.pe.Bruma.auth.AuthenticationResponse;
import com.pe.Bruma.security.jwt.JwtService; // <--- Asegúrate de tener este servicio
import com.pe.Bruma.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository repository;
    private final JwtService jwtService; // El servicio que genera los tokens
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // 1. Validar usuario y contraseña (Spring Security hace la magia aquí)
        // Si la contraseña está mal, esta línea lanza una excepción automáticamente.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Si pasamos la línea anterior, el usuario es válido. Lo buscamos en BD.
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // 3. Generamos el Token JWT
        var jwtToken = jwtService.generateToken(user);

        // 4. Devolvemos el token en la respuesta
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}