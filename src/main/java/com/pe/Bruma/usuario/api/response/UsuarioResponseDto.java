package com.pe.Bruma.usuario.api.response;

import com.pe.Bruma.rol.api.response.RolResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String celular;
    private LocalDate fechaNacimiento;
    private String estado;

    private List<RolResponseDto> roles;
}
