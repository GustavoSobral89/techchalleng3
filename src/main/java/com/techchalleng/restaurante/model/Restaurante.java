package com.techchalleng.restaurante.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurantes")
@Data
public class Restaurante {
    @Id
    private String id;
    private String nome;
    private String localizacao;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private int capacidade;
}
