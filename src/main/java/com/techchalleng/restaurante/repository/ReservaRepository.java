package com.techchalleng.restaurante.repository;

import com.techchalleng.restaurante.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByIdRestaurante(String idRestaurante);
}
