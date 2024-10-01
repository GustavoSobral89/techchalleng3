package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReservaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }

    @Test
    public void deveCriarReserva() throws Exception {
        String reservaJson = "{\"idRestaurante\":\"1\", \"idUsuario\":\"usuario123\", \"dataHora\":\"2024-01-01T12:00:00\", \"numeroPessoas\":4}";

        Reserva reserva = new Reserva();
        reserva.setIdRestaurante("1");
        reserva.setIdUsuario("usuario123");
        reserva.setDataHora(LocalDateTime.now());
        reserva.setNumeroPessoas(4);

        when(reservaService.criarReserva(any(Reserva.class))).thenReturn(reserva);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservaJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deveBuscarReservasPorRestaurante() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setIdRestaurante("1");
        reserva.setIdUsuario("usuario123");
        reserva.setDataHora(LocalDateTime.now());
        reserva.setNumeroPessoas(4);

        when(reservaService.buscarReservasPorRestaurante("1"))
                .thenReturn(Collections.singletonList(reserva));

        mockMvc.perform(get("/reservas/restaurante/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idRestaurante").value("1"));
    }

    @Test
    public void deveBuscarReservasPorStatus() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId("1");
        reserva.setIdRestaurante("1");
        reserva.setIdUsuario("usuario123");
        reserva.setStatus("PENDENTE");

        when(reservaService.buscarReservasPorStatus("PENDENTE"))
                .thenReturn(Collections.singletonList(reserva));

        mockMvc.perform(get("/reservas/status/PENDENTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("PENDENTE"));
    }

    @Test
    public void deveAtualizarStatusReserva() throws Exception {
        String statusJson = "\"CONFIRMADA\"";

        Reserva reserva = new Reserva();
        reserva.setId("1");
        reserva.setIdRestaurante("1");
        reserva.setStatus("CONFIRMADA"); // Novo status após a atualização

        when(reservaService.atualizarStatusReserva("1", "CONFIRMADA")).thenReturn(reserva);

        mockMvc.perform(put("/reservas/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(statusJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMADA")); // Verifique o novo status
    }
}
