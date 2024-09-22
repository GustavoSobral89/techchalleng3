package com.techchalleng.restaurante.repository;

import com.techchalleng.restaurante.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AvaliacaoRepository extends MongoRepository<Avaliacao, String> {
    List<Avaliacao> findByIdRestaurante(String idRestaurante);
}
