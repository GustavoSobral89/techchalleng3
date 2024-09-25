package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //No caso de Clean Architecture essa é a camada de UseCase
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva criarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarReservasPorRestaurante(String idRestaurante) {
        return reservaRepository.findByIdRestaurante(idRestaurante);
    }

    public List<Reserva> buscarReservasPorStatus(String status) {
        return reservaRepository.findByStatus(status);
    }

    public Reserva atualizarStatusReserva(String id, String status) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        reserva.setStatus(status);
        return reservaRepository.save(reserva);
    }
}