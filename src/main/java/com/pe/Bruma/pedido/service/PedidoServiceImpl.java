package com.pe.Bruma.pedido.service;

import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.mapper.MesaMaper;
import com.pe.Bruma.mesa.repository.MesaRepository;
import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoDetalleRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;
import com.pe.Bruma.pedido.entity.DetallePedido;
import com.pe.Bruma.pedido.entity.DetallePedidoId;
import com.pe.Bruma.pedido.entity.Pedido;
import com.pe.Bruma.pedido.mapper.PedidoMapper;
import com.pe.Bruma.pedido.repository.PedidoRepository;
import com.pe.Bruma.producto.entity.Producto;
import com.pe.Bruma.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private  PedidoRepository pedidoRepository;
    @Autowired
    private  MesaRepository mesaRepository;
    @Autowired
    private  ProductoRepository productoRepository;
    @Autowired
    private  MesaMaper mesaMapper;

    @Autowired
    private  PedidoMapper mapper;
    @Override
    public PedidoResponseDto createPedido(PedidoCreateRequestDto dto) {
        Mesa mesa = mesaRepository.findById(dto.getMesa_id()).orElse(null);
        mesa.setDisponible(false);
        mesaRepository.save(mesa);
        Pedido pedidoSave = Pedido.builder()
                .fecha_pedido(LocalDateTime.now())
                .total(BigDecimal.valueOf(0))
                .estado("Ocupado")
                .mesa(mesa)
                .build();
        pedidoRepository.save(pedidoSave);
        return mapper.toResponseDto(pedidoSave);
    }

    @Override
    public List<PedidoResponseDto> getAllPedido() {
        return pedidoRepository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public PedidoResponseDto getPedido(Long id) {
        Pedido entity = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return mapper.toResponseDto(entity);
    }

    @Override
    public void deletePedido(Long id) {
        Pedido entity = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException());
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponseDto updatePedido(Long id, PedidoUpdateRequestDto dto) {
        Pedido entity = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException());
        if(dto.getFechaPedido()!=null){
            entity.setFecha_pedido(dto.getFechaPedido());
        }
        if(dto.getEstado()!=null){
            entity.setEstado(dto.getEstado());
        }
        if(dto.getMesaId()!=null){
            Mesa mesa = mesaRepository.findById(dto.getMesaId())
                            .orElseThrow(()->new RuntimeException());
            mesa.setDisponible(false);
            Mesa mesaOld = entity.getMesa();
            mesaOld.setDisponible(true);
            mesaRepository.save(mesaOld);
            entity.setMesa(mesa);
            mesaRepository.save(mesa);
        }

        if(dto.getDetalles() != null){
            entity.getDetalle().clear();
            BigDecimal total = BigDecimal.ZERO;

            for(PedidoDetalleRequestDto detDto : dto.getDetalles()){
                Producto producto = productoRepository.findById(detDto.getProductoId())
                        .orElseThrow(()->new RuntimeException());

                DetallePedidoId detalleId = DetallePedidoId.builder()
                        .pedido_id(entity.getPedido_id())
                        .producto_id(producto.getId())
                        .build();
                DetallePedido detalle = DetallePedido.builder()
                        .id(detalleId)
                        .pedido(entity)
                        .producto(producto)
                        .cantidad(detDto.getCantidad())
                        .subtotal(
                                producto.getPrecio().multiply(new BigDecimal(detDto.getCantidad()))
                        )
                        .build();
                entity.getDetalle().add(detalle);
                total = total.add(detalle.getSubtotal());
            }
            entity.setTotal(total);
        }

        Pedido saved = pedidoRepository.save(entity);
        return mapper.toResponseDto(saved);
    }
}
