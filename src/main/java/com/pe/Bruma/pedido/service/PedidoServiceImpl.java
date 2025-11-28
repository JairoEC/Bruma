package com.pe.Bruma.pedido.service;

import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;
import com.pe.Bruma.pedido.mapper.PedidoMapper;
import com.pe.Bruma.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    private final PedidoMapper mapper;
    @Override
    public PedidoResponseDto createPedido(PedidoCreateRequestDto dto) {
        return null;
    }

    @Override
    public List<PedidoResponseDto> getAllPedido() {
        return List.of();
    }

    @Override
    public PedidoResponseDto getPedido(BigInteger id) {
        return null;
    }

    @Override
    public void deletePedido(BigInteger id) {

    }

    @Override
    public PedidoResponseDto updatePedido(BigInteger id, PedidoUpdateRequestDto dto) {
        return null;
    }
}
