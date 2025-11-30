package com.pe.Bruma.rol.mapper;

import com.pe.Bruma.rol.api.request.RolCreateRequestDto;
import com.pe.Bruma.rol.api.request.RolUpdateRequestDto;
import com.pe.Bruma.rol.api.response.RolResponseDto;
import com.pe.Bruma.rol.entity.Rol;
import org.mapstruct.*;
import org.springframework.jmx.export.annotation.ManagedNotification;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolMapper {

    // de Dto a Entity
    @Mapping(target = "estado", constant = "ACTIVO")
    @Mapping(target = "id", ignore = true)
    Rol toEntity(RolCreateRequestDto dto);

    // de Entity a Dto
    RolResponseDto toResponseDto(Rol entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RolUpdateRequestDto dto, @MappingTarget Rol entity);

}
