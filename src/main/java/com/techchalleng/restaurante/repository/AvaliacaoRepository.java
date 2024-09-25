package com.techchalleng.restaurante.repository;

import com.techchalleng.restaurante.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
//No caso de Clean Architecture essa é a camada de Gateway
public interface AvaliacaoRepository extends MongoRepository<Avaliacao, String> {
    List<Avaliacao> findByIdRestaurante(String idRestaurante);
}
