package com.pe.Bruma.usuario.service;

import com.pe.Bruma.usuario.api.request.UsuarioCreateRequestDto;
import com.pe.Bruma.usuario.api.request.UsuarioUpdateRequestDto;
import com.pe.Bruma.usuario.api.response.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDto createEmpleado (UsuarioCreateRequestDto dto);
    UsuarioResponseDto updateEmpleado (Long id, UsuarioUpdateRequestDto dto);

    List<UsuarioResponseDto> getAllEmpleados();
    UsuarioResponseDto getEmpleadoById (Long id);
    void deleteEmpleado (Long id);
}
