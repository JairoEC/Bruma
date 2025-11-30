package com.pe.Bruma.mesa.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesaCreateRequestDto {
    @NotBlank(message = "El nombre de la mesa no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombreMesa;

    @NotBlank(message = "La cantidad de personas no puede estar vacía")
    private String cantPersonas;

    @NotNull(message = "Debe indicar si está disponible")
    private Boolean disponible;
}
