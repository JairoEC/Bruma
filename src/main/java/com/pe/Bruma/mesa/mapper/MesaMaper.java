package com.pe.Bruma.mesa.mapper;

import com.pe.Bruma.mesa.api.request.MesaCreateRequestDto;
import com.pe.Bruma.mesa.api.request.MesaUpdateRequestDto;
import com.pe.Bruma.mesa.api.response.MesaResponseDto;
import com.pe.Bruma.mesa.entity.Mesa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MesaMaper {

    @Mapping(target = "id", ignore = true)
    Mesa toEntity(MesaCreateRequestDto dto);

    MesaResponseDto toResponseDto(Mesa entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MesaUpdateRequestDto dto, @MappingTarget Mesa entity);
}
