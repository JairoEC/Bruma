package com.pe.Bruma.producto.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    private String nombre;
    private Double precio;
    private String categoria;
    private Boolean activo;

    @Version
    private Integer version; // control de concurrencia optimista
}