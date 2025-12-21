package com.pe.Bruma.pedido.repository;

import com.pe.Bruma.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
