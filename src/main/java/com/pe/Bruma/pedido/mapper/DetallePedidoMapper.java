package com.pe.Bruma.pedido.mapper;

import com.pe.Bruma.pedido.api.response.DetallePedidoResponseDto;
import com.pe.Bruma.pedido.entity.DetallePedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {
    @Mappings({
            @Mapping(target = "productoId", source = "producto.id"),
            @Mapping(target = "nombreProducto", source = "producto.nombre"),
            @Mapping(target = "cantidad", source = "cantidad"),
            @Mapping(target = "subtotal", source = "subtotal")
    })
    DetallePedidoResponseDto toResponseDto(DetallePedido detalle);
    List<DetallePedidoResponseDto> toResponseList(List<DetallePedido> detalles);
}
