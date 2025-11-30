package com.pe.Bruma.empleado.entity;

import com.pe.Bruma.rol.entity.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name="empleado")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, length = 8, unique = true)
    private String dni;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(length = 9)
    private String celular;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 20)
    private String estado;

    // ManyToOne (Muchos empleados -> Un Rol)
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}