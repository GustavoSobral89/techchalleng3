package com.techchalleng.restaurante.utils;

import com.techchalleng.restaurante.model.Reserva;

import java.time.LocalDateTime;

public class Util {

    public static Reserva mockReservaSemId() {
        return Reserva.builder()
                .dataHora(LocalDateTime.parse("2024-09-24T19:30:00"))
                .idRestaurante("67890")
                .idUsuario("user123")
                .numeroPessoas(4)
                .status("PENDENTE")
                .build();
    }
}
