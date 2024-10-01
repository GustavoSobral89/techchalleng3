package com.techchalleng.restaurante.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.repository.ReservaRepository;
import com.techchalleng.restaurante.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaControllerIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void deveCriarReservaComSucesso() throws Exception {
        Reserva reserva = Reserva.builder()
                .idRestaurante("67890")
                .idUsuario("user123")
                .dataHora(LocalDateTime.now())
                .numeroPessoas(4)
                .status("PENDENTE")
                .build();

        when(reservaService.criarReserva(any(Reserva.class))).thenReturn(reserva);

        String responseContent = mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Reserva reservaResponse = objectMapper.readValue(responseContent, Reserva.class);

        assert reservaResponse.getStatus().equals("PENDENTE");
    }

    @Test
    void deveBuscarReservasPorRestauranteComSucesso() throws Exception {
        List<Reserva> reservas = Arrays.asList(
                Reserva.builder().id("1").idRestaurante("67890").build(),
                Reserva.builder().id("2").idRestaurante("67890").build()
        );

        when(reservaService.buscarReservasPorRestaurante("67890")).thenReturn(reservas);

        String responseContent = mockMvc.perform(get("/reservas/restaurante/67890"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Reserva[] reservasResponse = objectMapper.readValue(responseContent, Reserva[].class);
        assert reservasResponse.length == 2;
        assert reservasResponse[0].getId().equals("1");
        assert reservasResponse[1].getId().equals("2");
    }

    @Test
    void deveBuscarReservasPorStatusComSucesso() throws Exception {
        List<Reserva> reservas = Arrays.asList(
                Reserva.builder().id("1").status("PENDENTE").build(),
                Reserva.builder().id("2").status("PENDENTE").build()
        );

        when(reservaService.buscarReservasPorStatus("PENDENTE")).thenReturn(reservas);

        String responseContent = mockMvc.perform(get("/reservas/status/PENDENTE"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Reserva[] reservasResponse = objectMapper.readValue(responseContent, Reserva[].class);
        assert reservasResponse.length == 2;
        assert reservasResponse[0].getStatus().equals("PENDENTE");
        assert reservasResponse[1].getStatus().equals("PENDENTE");
    }

    @Test
    void deveAtualizarStatusReservaComSucesso() throws Exception {
        Reserva reserva = Reserva.builder()
                .id("12345")
                .idRestaurante("67890")
                .idUsuario("user123")
                .dataHora(LocalDateTime.now())
                .numeroPessoas(4)
                .status("CONFIRMADA")
                .build();

        when(reservaService.atualizarStatusReserva("12345", "CONFIRMADA")).thenReturn(reserva);

        String responseContent = mockMvc.perform(put("/reservas/12345/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"CONFIRMADA\""))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Reserva reservaResponse = objectMapper.readValue(responseContent, Reserva.class);
        assert reservaResponse.getStatus().equals("CONFIRMADA");
    }
}