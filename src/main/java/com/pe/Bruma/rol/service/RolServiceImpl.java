package com.pe.Bruma.rol.service;

import com.pe.Bruma.rol.api.request.RolCreateRequestDto;
import com.pe.Bruma.rol.api.request.RolUpdateRequestDto;
import com.pe.Bruma.rol.api.response.RolResponseDto;
import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.mapper.RolMapper;
import com.pe.Bruma.rol.repository.RolRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    //Inject dependencies
    private final RolRepository rolRepo;
    private final RolMapper rolMapper;


    @Override
    public RolResponseDto createRol(RolCreateRequestDto dto) {

        String rolLimpio = (dto.getNombre()!= null) ? dto.getNombre().trim() : null;

        if (rolRepo.existsByNombreIgnoreCase(rolLimpio)) {
            throw new IllegalArgumentException("El rol con nombre '" + rolLimpio + "' ya existe.");
        }

        Rol rolNuevo = rolMapper.toEntity(dto);
        rolNuevo.setNombre(rolLimpio);

        Rol rolGuardado = rolRepo.save(rolNuevo);
        return rolMapper.toResponseDto(rolGuardado);

    }

    @Override
    public RolResponseDto updateRol(Long id, RolUpdateRequestDto dto) {

        Rol rolActual = rolRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("El rol con id '" + id + "' no existe.")
        );

        String nombreLimpio = (dto.getNombre() != null) ? dto.getNombre().trim() : null;

        boolean nombreCambiado = !rolActual.getNombre().equalsIgnoreCase(nombreLimpio);

        if (nombreCambiado && rolRepo.existsByNombreIgnoreCase(nombreLimpio)) {
            throw new IllegalArgumentException("El rol con nombre '" + nombreLimpio + "' ya existe.");
        }

        rolMapper.updateEntityFromDto(dto, rolActual);
        rolActual.setNombre(nombreLimpio);

        Rol rolActualizado = rolRepo.save(rolActual);
        return rolMapper.toResponseDto(rolActualizado);

    }

    @Override
    @Transactional(readOnly = true)
    public List<RolResponseDto> getAllRol() {

        List<Rol> roles = rolRepo.findAll();
        return roles.stream()
                .map(rolMapper::toResponseDto)
                .toList();
    }

    @Override
    public RolResponseDto getRol(Long id) {

        Rol rolbuscado = rolRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("El rol con id '" + id + "' no existe.")
        );
        return rolMapper.toResponseDto(rolbuscado);
    }

    @Override
    public void deleteRol(Long id) {
        Rol rolBorrar = rolRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("El rol con id '" + id + "' no existe.")
        );

        rolBorrar.setEstado("INACTIVO");
        rolRepo.save(rolBorrar);

    }


}
