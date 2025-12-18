package com.pe.Bruma.pedido.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class DetallePedidoId implements Serializable {
    private Long pedido_id;
    private Long producto_id;
}
