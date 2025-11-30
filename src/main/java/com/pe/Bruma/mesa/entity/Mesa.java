package com.pe.Bruma.mesa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table( name = "mesa")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mesa_id")
    private Long id;

    @Column(name = "nombre_mesa", nullable = false)
    private String nombreMesa;

    @Column(name = "cant_personas", nullable = false)
    private String cantPersonas;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible;
}