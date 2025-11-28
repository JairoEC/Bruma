package com.pe.Bruma.pedido.service;

import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;

import java.math.BigInteger;
import java.util.List;

public interface PedidoService {
    PedidoResponseDto createPedido(PedidoCreateRequestDto dto);
    List<PedidoResponseDto> getAllPedido();
    PedidoResponseDto getPedido(BigInteger id);
    void deletePedido(BigInteger id);
    PedidoResponseDto updatePedido(BigInteger id, PedidoUpdateRequestDto dto);
}
