package com.techchalleng.restaurante.repository;

import com.techchalleng.restaurante.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
//No caso de Clean Architecture essa é a camada de Gateway
public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByIdRestaurante(String idRestaurante);
    List<Reserva> findByStatus(String status);
}
