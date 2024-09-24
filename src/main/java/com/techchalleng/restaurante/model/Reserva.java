package com.techchalleng.restaurante.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reservas")
@Data
@Schema(description = "Modelo que representa uma reserva de restaurante")
public class Reserva {
    @Id
    @Schema(description = "ID da reserva", example = "12345")
    private String id;
    @Schema(description = "ID do restaurante para a reserva", example = "67890")
    private String idRestaurante;
    @Schema(description = "ID do usuário que fez a reserva", example = "user123")
    private String idUsuario;
    @Schema(description = "Data e hora da reserva", example = "2024-09-24T19:30:00")
    private LocalDateTime dataHora;
    @Schema(description = "Número de pessoas na reserva", example = "4")
    private int numeroPessoas;
    @Schema(description = "Status da reserva", example = "PENDENTE", allowableValues = {"PENDENTE", "CONFIRMADA", "CANCELADA"})
    private String status; // Exemplo: "PENDENTE", "CONFIRMADA", "CANCELADA"
}