package com.pe.Bruma.empleado.service;

import com.pe.Bruma.empleado.api.request.EmpleadoCreateRequestDto;
import com.pe.Bruma.empleado.api.request.EmpleadoUpdateRequestDto;
import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;
import com.pe.Bruma.empleado.entity.Empleado;
import com.pe.Bruma.empleado.mapper.EmpleadoMapper;
import com.pe.Bruma.empleado.repository.EmpleadoRepository;
import com.pe.Bruma.rol.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepo;
    private final RolRepository rolRepo;
    private final EmpleadoMapper empleadoMapper;


    @Override
    public EmpleadoResponseDto createEmpleado(EmpleadoCreateRequestDto dto) {
        String nombreLimpio = (dto.getNombre() != null) ? dto.getNombre().trim() : null;
        String dniLimpio = (dto.getDni() != null) ? dto.getDni().trim() : null;
        String emailLimpio = (dto.getEmail() != null) ? dto.getEmail().trim() : null;

        if (empleadoRepo.existsByDni(dniLimpio)) {
            throw new IllegalArgumentException("El empleado con DNI '" + dniLimpio + "' ya existe.");
        }

        if (empleadoRepo.existsEmailIgnoreCase(emailLimpio)) {
            throw new IllegalArgumentException("El empleado con email '" + emailLimpio + "' ya existe.");
        }

        if(!rolRepo.existsById(dto.getRolId())) {
            throw new IllegalArgumentException("El rol con id '" + dto.getRolId() + "' no existe.");
        }

        Empleado empleadoNuevo = empleadoMapper.toEntity(dto);
        Empleado empleadoGuardado = empleadoRepo.save(empleadoNuevo);
        return empleadoMapper.toResponseDto(empleadoGuardado);
    }

    @Override
    public EmpleadoResponseDto updateEmpleado(Long id, EmpleadoUpdateRequestDto dto) {
        return null;
    }

    @Override
    public List<EmpleadoResponseDto> getAllEmpleado() {
        return List.of();
    }

    @Override
    public EmpleadoResponseDto getEmpleadoById(Long id) {
        return null;
    }

    @Override
    public void deleteEmpleado(Long id) {

    }
}
