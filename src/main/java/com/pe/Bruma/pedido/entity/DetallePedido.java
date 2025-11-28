package com.pe.Bruma.pedido.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class DetallePedido {

    private BigInteger pedido_id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    /*
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    */

}
