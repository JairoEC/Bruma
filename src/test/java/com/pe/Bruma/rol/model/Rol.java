package com.pe.Bruma.rol.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table("tb_roles")
public class Rol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;
}
