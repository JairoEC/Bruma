package com.pe.Bruma.pedido.api.controller;

import com.pe.Bruma.pedido.api.request.PedidoCreateRequestDto;
import com.pe.Bruma.pedido.api.request.PedidoUpdateRequestDto;
import com.pe.Bruma.pedido.api.response.PedidoResponseDto;
import com.pe.Bruma.pedido.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> listar(){
        List<PedidoResponseDto> pedidos = pedidoService.getAllPedido();
        return ResponseEntity.ok(pedidos);
    }
    @PostMapping
    public ResponseEntity<PedidoResponseDto> crear(@Valid @RequestBody PedidoCreateRequestDto dto){
        PedidoResponseDto entity = pedidoService.createPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
    @PutMapping("/{id}")
    public PedidoResponseDto actualizar(@PathVariable BigInteger id, @Valid @RequestBody PedidoUpdateRequestDto request){
        return pedidoService.updatePedido(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable BigInteger id){
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
