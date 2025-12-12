package com.pe.Bruma.rol.inicializar;

import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RolRepository rolRepository;
    @Override
    public void run(String... args) throws Exception {
        crearRol("ADMIN");
    }

    private void crearRol(String nombre){
        if(!rolRepository.existsByNombreIgnoreCase(nombre)){
            Rol rol = Rol.builder()
                    .nombre(nombre)
                    .estado("ACTIVO")
                    .build();
            rolRepository.save(rol);
        }
    }
}
