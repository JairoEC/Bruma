package com.pe.Bruma.auth;

import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.repository.MesaRepository;
import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.repository.RolRepository;
import com.pe.Bruma.usuario.entity.Usuario;
import com.pe.Bruma.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final MesaRepository mesaRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. Crear Roles
        if (rolRepository.count() == 0) {
            Rol adminRol = Rol.builder().nombre("ADMINISTRADOR").estado("ACTIVO").build();
            Rol userRol = Rol.builder().nombre("EMPLEADO").estado("ACTIVO").build();
            rolRepository.save(adminRol);
            rolRepository.save(userRol);
            System.out.println("✅ Roles creados");
        }

        // 2. Crear Usuario ADMIN
        if (usuarioRepository.findByEmail("admin@bruma.pe").isEmpty()) {

            // Busca el rol de forma segura
            Rol rolAdmin = rolRepository.findAll().stream()
                    .filter(r -> r.getNombre().toUpperCase().contains("ADMIN")) // Mejor usar UpperCase
                    .findFirst()
                    .orElse(null);

            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setApellidos("Sistema");
            admin.setDni("00000000"); // 8 dígitos estándar
            admin.setCelular("999999999"); // 9 dígitos estándar
            admin.setEmail("admin@bruma.pe");
            admin.setFechaNacimiento(LocalDate.now());

            // --- CORRECCIÓN CRÍTICA ---
            admin.setEstado("ACTIVO");  // Esto faltaba y causaba el error 500/ConstraintViolation
            admin.setActivo(true);
            // --------------------------

            admin.setPassword(passwordEncoder.encode("123456"));

            if (rolAdmin != null) {
                admin.setRoles(List.of(rolAdmin));
            }

            usuarioRepository.save(admin);
            System.out.println("✅ Usuario ADMIN creado correctamente: admin@bruma.pe");
        }

        if (mesaRepository.count() == 0) {
            for (int i = 1; i <= 8; i++) {
                Mesa mesa = Mesa.builder()
                        .nombreMesa("Mesa " + i)
                        .cantPersonas(i % 2 == 0 ? "4" : "2") // Unas de 4 y otras de 2
                        .disponible(i % 3 != 0) // Unas disponibles y otras no (para probar colores)
                        .build();
                mesaRepository.save(mesa);
            }
            System.out.println("✅ 8 Mesas de prueba creadas");
        }
    }
}