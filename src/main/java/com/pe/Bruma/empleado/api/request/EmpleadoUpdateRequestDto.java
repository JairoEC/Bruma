package com.pe.Bruma.empleado.api.request;

import com.pe.Bruma.empleado.api.response.EmpleadoResponseDto;
import com.pe.Bruma.empleado.entity.Empleado;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class EmpleadoUpdateRequestDto {


}
