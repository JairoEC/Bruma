package com.pe.Bruma.pedido.entity;

import com.pe.Bruma.mesa.entity.Mesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger pedido_id;
    private LocalDateTime fecha_pedido=LocalDateTime.now();
    private BigDecimal total;
    private String estado;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalle;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

}
