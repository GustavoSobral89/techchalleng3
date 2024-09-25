package com.techchalleng.restaurante.repository;

import com.techchalleng.restaurante.model.Restaurante;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
//No caso de Clean Architecture essa é a camada de Gateway
public interface RestauranteRepository extends MongoRepository<Restaurante, String> {
    List<Restaurante> findByNomeContainingOrLocalizacaoContainingOrTipoCozinhaContaining(String nome, String localizacao, String tipoCozinha);
}