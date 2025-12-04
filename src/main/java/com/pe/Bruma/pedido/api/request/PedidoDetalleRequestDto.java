package com.pe.Bruma.pedido.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoDetalleRequestDto {
    private Long productoId;
    private BigInteger cantidad;
}
