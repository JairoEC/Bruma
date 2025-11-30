package com.pe.Bruma.mesa.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaUpdateRequestDto {

    private String nombreMesa;
    private String cantPersonas;
    private Boolean disponible;
}
