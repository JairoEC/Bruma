package com.pe.Bruma.mesa.entity;

import com.pe.Bruma.pedido.entity.Pedido;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table( name = "mesa")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mesa {

    @Id
    private BigInteger mesa_id;
    private String nombre_mesa;
    private String cant_personas;
    private boolean disponible;

    @OneToMany(mappedBy = "mesa")
    private List<Pedido> pedidos;
}
