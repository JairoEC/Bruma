package com.pe.Bruma.pedido.api.response;

import com.pe.Bruma.pedido.entity.DetallePedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class PedidoResponseDto {
    private String pedido_id;
    private LocalDateTime fecha_pedido;
    private BigDecimal total;
    private boolean estado;
    private List<DetallePedido> detalle;
    private Long mesa_id;
}
