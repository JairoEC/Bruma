package com.pe.Bruma.empleado.api.request;

import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;
import com.pe.Bruma.empleado.entity.Empleado;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class EmpleadoUpdateRequestDto {


    @Pattern(regexp = ".*\\S.*", message = "El nombre no puede estar vacío ni tener solo espacios")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String nombre;

    @Pattern(regexp = ".*\\S.*", message = "Los apellidos no pueden estar vacíos ni tener solo espacios")
    @Size(max = 100, message = "Los apellidos no pueden exceder los 100 caracteres")
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    @Pattern(regexp = "\\d+", message = "El DNI solo puede contener números")
    private String dni;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Size(max = 100)
    private String email;

    @Size(min = 9, max = 9, message = "El celular debe tener 9 dígitos")
    private String celular;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long rolId;

    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estado debe ser 'ACTIVO' o 'INACTIVO'")
    private String estado;
}
