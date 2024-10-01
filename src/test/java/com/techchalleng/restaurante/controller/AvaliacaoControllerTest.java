package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Avaliacao;
import com.techchalleng.restaurante.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AvaliacaoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AvaliacaoService avaliacaoService;

    @InjectMocks
    private AvaliacaoController avaliacaoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(avaliacaoController).build();
    }

    @Test
    public void deveCriarAvaliacao() throws Exception {
        String avaliacaoJson = "{\"idRestaurante\":\"1\", \"idUsuario\":\"usuario123\", \"nota\":5, \"comentario\":\"Ótimo restaurante!\"}";

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdRestaurante("1");
        avaliacao.setIdUsuario("usuario123");
        avaliacao.setNota(5);
        avaliacao.setComentario("Ótimo restaurante!");

        when(avaliacaoService.criarAvaliacao(any(Avaliacao.class))).thenReturn(avaliacao);

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(avaliacaoJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deveBuscarAvaliacoesPorRestaurante() throws Exception {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdRestaurante("1");
        avaliacao.setIdUsuario("usuario123");
        avaliacao.setNota(5);
        avaliacao.setComentario("Ótimo restaurante!");

        when(avaliacaoService.buscarAvaliacoesPorRestaurante("1")).thenReturn(Collections.singletonList(avaliacao));

        mockMvc.perform(get("/avaliacoes/restaurante/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idRestaurante").value("1"));
    }
}
