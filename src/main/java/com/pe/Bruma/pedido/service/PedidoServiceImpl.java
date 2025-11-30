package com.pe.Bruma.pedido.service;

import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.mapper.MesaMaper;
import com.pe.Bruma.mesa.repository.MesaRepository;
import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;
import com.pe.Bruma.pedido.entity.Pedido;
import com.pe.Bruma.pedido.mapper.PedidoMapper;
import com.pe.Bruma.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;
    private final MesaMaper mesaMapper;

    private final PedidoMapper mapper;
    @Override
    public PedidoResponseDto createPedido(PedidoCreateRequestDto dto) {
        Mesa mesa = mesaRepository.findById(dto.getMesa_id()).orElse(null);
        Pedido pedidoSave = Pedido.builder()
                .total(BigDecimal.valueOf(0))
                .estado("Ocupado")
                .mesa(mesa)
                .build();
        pedidoRepository.save(pedidoSave);
        return mapper.toResponseDto(pedidoSave);
    }

    @Override
    public List<PedidoResponseDto> getAllPedido() {
        return pedidoRepository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public PedidoResponseDto getPedido(BigInteger id) {
        Pedido entity = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return mapper.toResponseDto(entity);
    }

    @Override
    public void deletePedido(BigInteger id) {
        Pedido entity = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException());
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponseDto updatePedido(BigInteger id, PedidoUpdateRequestDto dto) {
        Pedido entity = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException());
        mapper.updateEntityFromDto(dto,entity);
        return mapper.toResponseDto(entity);
    }
}
