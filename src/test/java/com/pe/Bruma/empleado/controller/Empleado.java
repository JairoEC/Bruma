package com.pe.Bruma.empleado.controller;

import com.pe.Bruma.rol.model.Rol;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table("tb_empleado")
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @OneToMany
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}
