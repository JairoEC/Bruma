package com.pe.Bruma.usuario.repository;

import com.pe.Bruma.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByDni(String dni);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByDni(String dni);

    Optional<Usuario> findByEmail(String username);
}
