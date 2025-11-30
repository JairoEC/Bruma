package com.pe.Bruma.mesa.entity;

import com.pe.Bruma.pedido.entity.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "mesa")
    private List<Pedido> pedidos;
}