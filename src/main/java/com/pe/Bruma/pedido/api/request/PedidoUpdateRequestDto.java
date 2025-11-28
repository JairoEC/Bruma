package com.pe.Bruma.pedido.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoUpdateRequestDto {
    private LocalDateTime fechaPedido;
    private BigInteger mesaId;
    private List<PedidoDetalleRequestDto> detalles;
    private String estado;
}
