package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Restaurante;
import com.techchalleng.restaurante.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //No caso de Clean Architecture essa é a camada de UseCase
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante cadastrarRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> buscarRestaurantes(String nome, String localizacao, String tipoCozinha) {
        return restauranteRepository.findByNomeContainingOrLocalizacaoContainingOrTipoCozinhaContaining(nome, localizacao, tipoCozinha);
    }
}
