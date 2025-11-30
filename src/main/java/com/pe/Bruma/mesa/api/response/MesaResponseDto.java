package com.pe.Bruma.mesa.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaResponseDto {

    private Long id;
    private String nombreMesa;
    private String cantPersonas;
    private Boolean disponible;
}
