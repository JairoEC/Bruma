package com.pe.Bruma.mesa.service;

import com.pe.Bruma.mesa.entity.Mesa;
import com.pe.Bruma.mesa.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;}

    public List<Mesa> obtenerTodas() {
        return mesaRepository.findAll();}

    public Mesa crear(Mesa mesa) {
        return mesaRepository.save(mesa);}

    public Mesa actualizar(Mesa mesa) {
        return mesaRepository.save(mesa);}

    public void eliminar(Long id) {
        mesaRepository.deleteById(id);
    }
}
