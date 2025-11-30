package com.pe.Bruma.producto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

}
