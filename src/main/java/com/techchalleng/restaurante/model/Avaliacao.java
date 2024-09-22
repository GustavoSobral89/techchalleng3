package com.techchalleng.restaurante.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "avaliacoes")
public class Avaliacao {
    @Id
    private String id;
    private String idRestaurante;
    private String idUsuario;
    private int nota; // de 1 a 5
    private String comentario;
}