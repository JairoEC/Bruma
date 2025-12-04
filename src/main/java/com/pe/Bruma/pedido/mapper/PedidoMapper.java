package com.pe.Bruma.pedido.mapper;

import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;
import com.pe.Bruma.pedido.entity.Pedido;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {DetallePedidoMapper.class})
public interface PedidoMapper {
    @Mappings({
            @Mapping(target = "mesa", source = "mesa_id", qualifiedByName = "mapMesaId"),
            @Mapping(target = "detalle",ignore = true)
    })
    Pedido toEntity(PedidoCreateRequestDto dto);
    @Mappings({
            @Mapping(target = "detalle", source = "detalle"),
            @Mapping(target = "mesa_id", source = "mesa.id")
    })
    PedidoResponseDto toResponseDto(Pedido entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PedidoUpdateRequestDto dto, @MappingTarget Pedido entity);
    @Named("mapMesaId")
    default Mesa mapMesaId(Long id){
        if(id==null) return null;
        Mesa mesa = new Mesa();
        mesa.setId(id);
        return mesa;
    }
}
