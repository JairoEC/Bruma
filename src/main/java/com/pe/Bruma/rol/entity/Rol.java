package com.pe.Bruma.rol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name="rol")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Rol implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Override
    public String getAuthority() {
        return nombre;
    }
}
