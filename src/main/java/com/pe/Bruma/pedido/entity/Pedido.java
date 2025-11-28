package com.pe.Bruma.pedido.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private BigInteger pedido_id;
    private LocalDateTime fecha_pedido;
    private BigDecimal total;
    private String estado;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalle;

}
