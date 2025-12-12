package com.pe.Bruma.rol.repository;

import com.pe.Bruma.rol.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository <Rol, Long >{

//METODOS PERSONALIZADOS
    boolean existsByNombreIgnoreCase(String nombre);

}
