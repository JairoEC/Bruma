package com.pe.Bruma.rol.service;

import com.pe.Bruma.rol.api.request.RolCreateRequestDto;
import com.pe.Bruma.rol.api.request.RolUpdateRequestDto;
import com.pe.Bruma.rol.api.response.RolResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolService {
    RolResponseDto createRol(RolCreateRequestDto dto);
    List<RolResponseDto> getAllRol();
    RolResponseDto getRol(Long id);
    void deleteRol(Long id);
    RolResponseDto updateRol(Long id, RolUpdateRequestDto dto);
}
