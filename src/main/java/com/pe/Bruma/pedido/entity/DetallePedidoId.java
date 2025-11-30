package com.pe.Bruma.pedido.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class DetallePedidoId implements Serializable {
    private BigInteger pedido_id;
    private BigInteger producto_id;
}
