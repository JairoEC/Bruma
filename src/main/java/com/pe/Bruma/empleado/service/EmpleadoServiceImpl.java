package com.pe.Bruma.empleado.service;

import com.pe.Bruma.empleado.api.request.EmpleadoCreateRequestDto;
import com.pe.Bruma.empleado.api.request.EmpleadoUpdateRequestDto;
import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;
import com.pe.Bruma.empleado.entity.Empleado;
import com.pe.Bruma.empleado.mapper.EmpleadoMapper;
import com.pe.Bruma.empleado.repository.EmpleadoRepository;
import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.repository.RolRepository;
import org.springframework.transaction.annotation.Transactional;
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

        if (empleadoRepo.existsByEmailIgnoreCase(emailLimpio)) {
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
        Empleado empleadoActual = empleadoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con id '" + id + "' no existe."));


        Rol nuevoRol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new IllegalArgumentException("El rol con id '" + dto.getRolId() + "' no existe."));

        empleadoActual.setRol(nuevoRol);
        empleadoMapper.updateEntityFromDto(dto, empleadoActual);
        Empleado empleadoActualizado = empleadoRepo.save(empleadoActual);
        return empleadoMapper.toResponseDto(empleadoActualizado);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoResponseDto> getAllEmpleados() {
        return empleadoRepo.findAll().stream()
                .map(empleadoMapper::toResponseDto)
                .toList();
    }

    @Override
    public EmpleadoResponseDto getEmpleadoById(Long id) {
        Empleado empleadoBuscado = empleadoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con id '" + id + "' no existe."));
        return empleadoMapper.toResponseDto(empleadoBuscado);
    }

    @Override
    @Transactional
    public void deleteEmpleado(Long id) {
        Empleado empleadoBuscado = empleadoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con id '" + id + "' no existe."));
        empleadoBuscado.setEstado("INACTIVO");
        empleadoRepo.save(empleadoBuscado);

    }
}
