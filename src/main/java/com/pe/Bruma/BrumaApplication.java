package com.pe.Bruma;

import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.repository.RolRepository;
import com.pe.Bruma.usuario.entity.Usuario;
import com.pe.Bruma.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor // 1. Lombok inyecta los repositorios automáticamente
public class BrumaApplication implements CommandLineRunner { // 2. Implementamos la interfaz aquí

	private final UsuarioRepository usuarioRepo;
	private final RolRepository rolRepo;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BrumaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// --- AQUÍ VA LA LÓGICA DE CREACIÓN AUTOMÁTICA ---

		// A. Verificar o Crear el ROL 'ADMIN'
		Rol rolAdmin = rolRepo.findAll().stream()
				.filter(r -> "ADMIN".equalsIgnoreCase(r.getNombre()))
				.findFirst()
				.orElseGet(() -> {
					Rol nuevoRol = new Rol();
					nuevoRol.setNombre("ADMIN");
					nuevoRol.setEstado("ACTIVO");
					return rolRepo.save(nuevoRol);
				});

		// B. Verificar si existe el usuario Admin (por DNI)
		String dniAdmin = "12345678"; // <--- ESTE SERÁ TU USUARIO PARA LOGUEARTE
		if (!usuarioRepo.existsByDni(dniAdmin)) {

			Usuario admin = Usuario.builder()
					.nombre("Administrador")
					.apellidos("Sistema")
					.dni(dniAdmin)
					.email("admin@bruma.pe")
					.celular("999888777")
					.password(passwordEncoder.encode("123456")) // <--- CONTRASEÑA: 123456
					.estado("ACTIVO")
					.activo(true)
					.roles(List.of(rolAdmin))
					.build();

			usuarioRepo.save(admin);
			System.out.println("✅ USUARIO MAESTRO CREADO: User: 12345678 | Pass: 123456");
		} else {
			System.out.println("ℹ️ El usuario maestro ya existe. No se duplicará.");
		}
	}
}