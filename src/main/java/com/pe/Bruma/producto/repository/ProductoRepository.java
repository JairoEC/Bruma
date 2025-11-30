package com.pe.Bruma.producto.repository;

import com.pe.Bruma.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
