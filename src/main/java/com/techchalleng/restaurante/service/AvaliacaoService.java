package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Avaliacao;
import com.techchalleng.restaurante.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //No caso de Clean Architecture essa é a camada de UseCase
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> buscarAvaliacoesPorRestaurante(String idRestaurante) {
        return avaliacaoRepository.findByIdRestaurante(idRestaurante);
    }
}