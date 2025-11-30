package com.pe.Bruma.empleado.mapper;

import com.pe.Bruma.empleado.api.request.EmpleadoCreateRequestDto;
import com.pe.Bruma.empleado.api.request.EmpleadoUpdateRequestDto;
import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;
import com.pe.Bruma.empleado.entity.Empleado;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmpleadoMapper {
    // 1. DE DTO A ENTIDAD (CREAR)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "ACTIVO")
    @Mapping(source = "rolId", target = "rol.id")
    Empleado toEntity(EmpleadoCreateRequestDto dto);

    // 2. DE ENTIDAD A DTO (RESPUESTA)
    @Mapping(source = "rol.id", target = "rolId")
    @Mapping(source = "rol.nombre", target = "rolNombre")
    EmpleadoResponseDto toResponseDto(Empleado entity);

    // 3. UPDATE (ACTUALIZAR)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EmpleadoUpdateRequestDto dto, @MappingTarget Empleado entity);


}
