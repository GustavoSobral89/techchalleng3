package com.techchalleng.restaurante.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reservas")
@Data
public class Reserva {
    @Id
    private String id;
    private String idRestaurante;
    private String idUsuario;
    private LocalDateTime dataHora;
    private int numeroPessoas;
}