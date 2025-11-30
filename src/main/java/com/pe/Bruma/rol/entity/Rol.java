package com.pe.Bruma.rol.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class Rol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
}
