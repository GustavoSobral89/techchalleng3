package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Avaliacao;
import com.techchalleng.restaurante.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AvaliacaoServiceTest {
    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    private Avaliacao avaliacao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        avaliacao = new Avaliacao();
        avaliacao.setIdRestaurante("1");
        avaliacao.setIdUsuario("usuario123");
        avaliacao.setNota(5);
        avaliacao.setComentario("Ótimo restaurante!");
    }

    @Test
    public void deveCriarAvaliacao() {
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);
        Avaliacao resultado = avaliacaoService.criarAvaliacao(avaliacao);
        assertEquals(avaliacao.getIdRestaurante(), resultado.getIdRestaurante());
    }

    @Test
    public void deveBuscarAvaliacoesPorRestaurante() {
        when(avaliacaoRepository.findByIdRestaurante("1")).thenReturn(Collections.singletonList(avaliacao));
        List<Avaliacao> resultado = avaliacaoService.buscarAvaliacoesPorRestaurante("1");
        assertEquals(1, resultado.size());
        assertEquals(avaliacao.getIdRestaurante(), resultado.get(0).getIdRestaurante());
    }
}
