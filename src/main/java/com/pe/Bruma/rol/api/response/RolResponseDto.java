package com.pe.Bruma.rol.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class RolResponseDto {
    private Long id;
    private String nombre;
    private String estado;
}
