package com.pe.Bruma.pedido.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class DetallePedidoResponseDto {
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal subtotal;
}
