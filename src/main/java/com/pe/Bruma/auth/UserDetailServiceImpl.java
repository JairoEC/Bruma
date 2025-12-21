package com.pe.Bruma.auth;

import com.pe.Bruma.usuario.repository.UsuarioRepository; // 2. Importa TU repositorio
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 3. CORRECCIÓN: Usamos 'findByEmail' porque tu entidad tiene el campo 'email'.
        // El parámetro 'username' aquí trae lo que el usuario escribió en el login (el correo).
        return usuarioRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));
    }
}