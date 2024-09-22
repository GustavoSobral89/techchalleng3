package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reserva = new Reserva();
        reserva.setIdRestaurante("1");
        reserva.setIdUsuario("usuario123");
        reserva.setDataHora(LocalDateTime.now());
        reserva.setNumeroPessoas(4);
    }

    @Test
    public void deveCriarReserva() {
        when(reservaRepository.save(reserva)).thenReturn(reserva);
        Reserva resultado = reservaService.criarReserva(reserva);
        assertEquals(reserva.getIdRestaurante(), resultado.getIdRestaurante());
    }

    @Test
    public void deveBuscarReservasPorRestaurante() {
        when(reservaRepository.findByIdRestaurante("1")).thenReturn(Collections.singletonList(reserva));
        List<Reserva> resultado = reservaService.buscarReservasPorRestaurante("1");
        assertEquals(1, resultado.size());
        assertEquals(reserva.getIdRestaurante(), resultado.get(0).getIdRestaurante());
    }
}