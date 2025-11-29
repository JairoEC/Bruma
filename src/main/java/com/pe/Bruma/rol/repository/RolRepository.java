package com.pe.Bruma.rol.repository;

import com.pe.Bruma.rol.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository <Rol, Integer >{

//METODOS PERSONALIZADOS
    Boolean existByNombre(String nombre);


}
