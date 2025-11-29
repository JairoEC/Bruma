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
    private Long id;

    private String nombre;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private Rol rol;
}
