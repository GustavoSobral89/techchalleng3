package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva criarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarReservasPorRestaurante(String idRestaurante) {
        return reservaRepository.findByIdRestaurante(idRestaurante);
    }
}