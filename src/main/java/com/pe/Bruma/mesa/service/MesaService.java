package com.pe.Bruma.mesa.service;

import com.pe.Bruma.mesa.api.request.MesaCreateRequestDto;
import com.pe.Bruma.mesa.api.request.MesaUpdateRequestDto;
import com.pe.Bruma.mesa.api.response.MesaResponseDto;
import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  MesaService {
    MesaResponseDto createMesa(MesaCreateRequestDto dto);
    List<MesaResponseDto> getAllMesa();
    MesaResponseDto updateMesa(Long id, MesaUpdateRequestDto dto);
    void deleteMesa(Long id);
}
