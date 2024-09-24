package com.techchalleng.restaurante.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "avaliacoes")
@Schema(description = "Modelo que representa uma avaliação de restaurante")
public class Avaliacao {
    @Id
    @Schema(description = "ID da avaliação", example = "12345")
    private String id;
    @Schema(description = "ID do restaurante avaliado", example = "67890")
    private String idRestaurante;
    @Schema(description = "ID do usuário que fez a avaliação", example = "user123")
    private String idUsuario;
    @Schema(description = "Nota da avaliação, de 1 a 5", example = "4")
    private int nota; // de 1 a 5
    @Schema(description = "Comentário da avaliação", example = "Ótima comida e atendimento!")
    private String comentario;
}