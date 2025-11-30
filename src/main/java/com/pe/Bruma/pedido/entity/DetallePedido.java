package com.pe.Bruma.pedido.entity;

import com.pe.Bruma.producto.entity.Producto;
import jakarta.persistence.*;
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
    @EmbeddedId
    private DetallePedidoId id;

    private BigInteger cantidad;

    @MapsId("pedido_id")
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @MapsId("producto_id")
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
