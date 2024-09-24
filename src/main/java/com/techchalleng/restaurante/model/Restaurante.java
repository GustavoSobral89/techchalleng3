package com.techchalleng.restaurante.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurantes")
@Data
@Schema(description = "Modelo que representa um restaurante")
public class Restaurante {
    @Id
    @Schema(description = "ID do restaurante", example = "12345")
    private String id;
    @Schema(description = "Nome do restaurante", example = "Restaurante Exemplo")
    private String nome;
    @Schema(description = "Localização do restaurante", example = "Rua das Flores, 123")
    private String localizacao;
    @Schema(description = "Tipo de cozinha do restaurante", example = "Italiana")
    private String tipoCozinha;
    @Schema(description = "Horário de funcionamento do restaurante", example = "Seg a Sex: 11h - 23h")
    private String horarioFuncionamento;
    @Schema(description = "Capacidade máxima do restaurante", example = "100")
    private int capacidade;
}