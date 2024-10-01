package com.techchalleng.restaurante.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchalleng.restaurante.model.Avaliacao;
import com.techchalleng.restaurante.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AvaliacaoControllerIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvaliacaoService avaliacaoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCriarAvaliacao() throws Exception {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdRestaurante("67890");
        avaliacao.setIdUsuario("user123");
        avaliacao.setNota(4);
        avaliacao.setComentario("Ótima comida e atendimento!");

        when(avaliacaoService.criarAvaliacao(any(Avaliacao.class))).thenReturn(avaliacao);

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avaliacao)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRestaurante").value("67890"))
                .andExpect(jsonPath("$.idUsuario").value("user123"))
                .andExpect(jsonPath("$.nota").value(4))
                .andExpect(jsonPath("$.comentario").value("Ótima comida e atendimento!"));
    }

    @Test
    public void testBuscarAvaliacoesPorRestaurante() throws Exception {
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setId("66fa1f89212e803020e0f130");
        avaliacao1.setIdRestaurante("67890");
        avaliacao1.setIdUsuario("user123");
        avaliacao1.setNota(4);
        avaliacao1.setComentario("Ótima comida e atendimento!");

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setId("66faa5cd077c580da934b2d2");
        avaliacao2.setIdRestaurante("67890");
        avaliacao2.setIdUsuario("user123");
        avaliacao2.setNota(4);
        avaliacao2.setComentario("Ótima comida e atendimento!");

        List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
        when(avaliacaoService.buscarAvaliacoesPorRestaurante("67890")).thenReturn(avaliacoes);

        mockMvc.perform(get("/avaliacoes/restaurante/67890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("66fa1f89212e803020e0f130")) // Altere o valor esperado
                .andExpect(jsonPath("$[0].idRestaurante").value("67890"))
                .andExpect(jsonPath("$[0].idUsuario").value("user123"))
                .andExpect(jsonPath("$[0].nota").value(4))
                .andExpect(jsonPath("$[0].comentario").value("Ótima comida e atendimento!"));
    }
}
