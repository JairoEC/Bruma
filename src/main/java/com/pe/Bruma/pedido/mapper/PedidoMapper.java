package com.pe.Bruma.pedido.mapper;

import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;
import com.pe.Bruma.pedido.entity.Pedido;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mappings({
            //@Mapping(target = "mesa", ignore = true),
            @Mapping(target = "detalle",ignore = true)
    })
    Pedido toEntity(PedidoCreateRequestDto dto);
    @Mapping(target = "detalle", source = "detalle")
    PedidoResponseDto toResponseDto(Pedido entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PedidoUpdateRequestDto dto, @MappingTarget Pedido entity);
}
