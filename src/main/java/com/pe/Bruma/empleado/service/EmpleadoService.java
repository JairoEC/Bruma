package com.pe.Bruma.empleado.service;

import com.pe.Bruma.empleado.api.request.EmpleadoCreateRequestDto;
import com.pe.Bruma.empleado.api.request.EmpleadoUpdateRequestDto;
import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;

import java.util.List;

public interface EmpleadoService {
    EmpleadoResponseDto createEmpleado (EmpleadoCreateRequestDto dto);
    EmpleadoResponseDto updateEmpleado (Long id, EmpleadoUpdateRequestDto dto);

    List<EmpleadoResponseDto> getAllEmpleado();
    EmpleadoResponseDto getEmpleadoById (Long id);
    void deleteEmpleado (Long id);
}
