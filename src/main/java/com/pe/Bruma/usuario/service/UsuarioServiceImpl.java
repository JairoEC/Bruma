package com.pe.Bruma.usuario.service;

import com.pe.Bruma.security.service.Impl.UserDetailServiceImpl;
import com.pe.Bruma.usuario.api.request.UsuarioCreateRequestDto;
import com.pe.Bruma.usuario.api.request.UsuarioUpdateRequestDto;
import com.pe.Bruma.usuario.api.response.UsuarioResponseDto;
import com.pe.Bruma.usuario.entity.Usuario;
import com.pe.Bruma.usuario.mapper.UsuarioMapper;
import com.pe.Bruma.usuario.repository.UsuarioRepository;
import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.repository.RolRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailServiceImpl userDetailService;

    @Override
    public UsuarioResponseDto createEmpleado(UsuarioCreateRequestDto dto) {
        String nombreLimpio = (dto.getNombre() != null) ? dto.getNombre().trim() : null;
        String dniLimpio = (dto.getDni() != null) ? dto.getDni().trim() : null;
        String emailLimpio = (dto.getEmail() != null) ? dto.getEmail().trim() : null;

        if (usuarioRepo.existsByDni(dniLimpio)) {
            throw new IllegalArgumentException("El empleado con DNI '" + dniLimpio + "' ya existe.");
        }

        if (usuarioRepo.existsByEmailIgnoreCase(emailLimpio)) {
            throw new IllegalArgumentException("El empleado con email '" + emailLimpio + "' ya existe.");
        }

        for(Long idRol : dto.getRoles()){
            if(!rolRepo.existsById(idRol)){
                throw new IllegalArgumentException("El rol con id "+idRol + " no existe");
            }
        }

        Usuario usuarioNuevo = usuarioMapper.toEntity(dto);
        usuarioNuevo.setEstado("ACTIVO");
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            usuarioNuevo.setPassword(passwordEncoder.encode(dto.getPassword()));
        }else{
            throw new IllegalArgumentException("Es necesario crear una contraseña");
        }

        if(dto.getRoles() != null && !dto.getRoles().isEmpty()){
            List<Rol> roles = rolRepo.findAllById(dto.getRoles());

            usuarioNuevo.setRoles(roles);
        }


        Usuario usuarioGuardado = usuarioRepo.save(usuarioNuevo);
        return usuarioMapper.toResponseDto(usuarioGuardado);
    }

    @Override
    public UsuarioResponseDto updateEmpleado(Long id, UsuarioUpdateRequestDto dto) {
        Usuario usuarioActual = usuarioRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con id '" + id + "' no existe."));


        Rol nuevoRol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new IllegalArgumentException("El rol con id '" + dto.getRolId() + "' no existe."));

        String passwordActual = usuarioActual.getPassword();

        usuarioMapper.updateEntityFromDto(dto, usuarioActual);
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            usuarioActual.setPassword(passwordEncoder.encode(dto.getPassword()));
        }else{
            usuarioActual.setPassword(passwordActual);
        }

        if(usuarioActual.getRoles() == null){
            usuarioActual.setRoles(new ArrayList<>());
        }
        if(!usuarioActual.getRoles().contains(nuevoRol)){
            usuarioActual.getRoles().add(nuevoRol);
        }

        Usuario usuarioActualizado = usuarioRepo.save(usuarioActual);

        return usuarioMapper.toResponseDto(usuarioActualizado);
    }


    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> getAllEmpleados() {
        return usuarioRepo.findAll().stream()
                .map(usuarioMapper::toResponseDto)
                .toList();
    }

    @Override
    public UsuarioResponseDto getEmpleadoById(Long id) {
        Usuario usuarioBuscado = usuarioRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con id '" + id + "' no existe."));
        return usuarioMapper.toResponseDto(usuarioBuscado);
    }

    @Override
    @Transactional
    public void deleteEmpleado(Long id) {
        Usuario usuarioBuscado = usuarioRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con id '" + id + "' no existe."));
        usuarioBuscado.setEstado("INACTIVO");
        usuarioRepo.save(usuarioBuscado);

    }
    @Override
    public UsuarioResponseDto findByDni(String dni){
        Usuario usuario = usuarioRepo.findByDni(dni)
                .orElseThrow(()-> new UsernameNotFoundException("No encontrado"));
        return usuarioMapper.toResponseDto(usuario);
    }
}
