package com.pe.Bruma.empleado.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponseDto {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String celular;
    private LocalDate fechaNacimiento;
    private String estado;

    private Long rolId;
    private String rolNombre;
}
