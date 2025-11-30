package com.pe.Bruma.empleado.repository;

import com.pe.Bruma.empleado.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    boolean existsEmailIgnoreCase(String email);
    boolean existsByDni(String dni);
}
