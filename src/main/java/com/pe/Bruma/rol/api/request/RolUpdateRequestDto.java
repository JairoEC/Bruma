package com.pe.Bruma.rol.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolUpdateRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 20, message = "El estado no puede tener más de 20 caracteres")
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estado debe ser 'ACTIVO' o 'INACTIVO'")
    private String estado;
}
