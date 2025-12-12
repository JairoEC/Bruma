package com.pe.Bruma.usuario.mapper;

import com.pe.Bruma.rol.api.response.RolResponseDto;
import com.pe.Bruma.rol.entity.Rol;
import com.pe.Bruma.rol.mapper.RolMapper;
import com.pe.Bruma.usuario.api.request.UsuarioCreateRequestDto;
import com.pe.Bruma.usuario.api.request.UsuarioUpdateRequestDto;
import com.pe.Bruma.usuario.api.response.UsuarioResponseDto;
import com.pe.Bruma.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    // 1. DE DTO A ENTIDAD (CREAR)
    @Mapping(target = "id", ignore = true)           // Se genera automáticamente
    @Mapping(target = "estado", ignore = true)       // Se establecerá en servicio
    @Mapping(target = "activo", ignore = true)       // Se establecerá en servicio
    @Mapping(target = "password", ignore = true)     // Se codificará en servicio
    @Mapping(target = "roles", ignore = true)
    Usuario toEntity(UsuarioCreateRequestDto dto);

    // 2. DE ENTIDAD A DTO (RESPUESTA)
    @Mapping(source = "roles", target = "roles", qualifiedByName="mapRoles")
    UsuarioResponseDto toResponseDto(Usuario entity);

    // 3. UPDATE (ACTUALIZAR)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UsuarioUpdateRequestDto dto, @MappingTarget Usuario entity);

    @Named("mapRoles")
    default List<RolResponseDto> mapRoles(List<Rol> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        List<RolResponseDto> result = roles.stream()
                .map(rol -> RolResponseDto.builder()
                        .id(rol.getId())
                        .nombre(rol.getNombre())
                        .estado(rol.getEstado())
                        .build())
                .toList();
        return result;
    }

}
