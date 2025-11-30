package com.pe.Bruma.rol.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolResponseDto {
    private Integer id;
    private String nombre;
    private String estado;
}
