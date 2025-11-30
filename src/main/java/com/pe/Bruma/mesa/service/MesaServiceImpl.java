package com.pe.Bruma.mesa.service;

import com.pe.Bruma.mesa.api.request.MesaCreateRequestDto;
import com.pe.Bruma.mesa.api.request.MesaUpdateRequestDto;
import com.pe.Bruma.mesa.api.response.MesaResponseDto;
import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.mapper.MesaMaper;
import com.pe.Bruma.mesa.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MesaServiceImpl implements MesaService{

    private final MesaRepository mesaRepository;
    private final MesaMaper mapper;

    @Override
    public MesaResponseDto createMesa(MesaCreateRequestDto dto) {
        Mesa entity = mapper.toEntity(dto);
        Mesa saved = mesaRepository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public List<MesaResponseDto> getAllMesa() {
        return mesaRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public MesaResponseDto updateMesa(Long id, MesaUpdateRequestDto dto) {
        Mesa entity = mesaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Mesa no encontrada"));

        mapper.updateEntityFromDto(dto, entity);

        Mesa saved = mesaRepository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public void deleteMesa(Long id) {
        mesaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Mesa no encontrada"));

        mesaRepository.deleteById(id);
    }
}
