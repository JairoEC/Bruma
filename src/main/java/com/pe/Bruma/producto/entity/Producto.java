package com.pe.Bruma.producto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Entity
@Table(name = "producto")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    private String nombre;
    private BigDecimal precio;
    private String categoria;
    private Boolean activo;

    @Version
    private Integer version; // control de concurrencia optimista
}