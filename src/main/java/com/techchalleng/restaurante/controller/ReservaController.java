package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Cria uma nova reserva", description = "Adiciona uma nova reserva para um restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@Parameter(description = "Dados da reserva a ser criada")
                                                @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.criarReserva(reserva));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @GetMapping("/restaurante/{idRestaurante}")
    public ResponseEntity<List<Reserva>> buscarReservasPorRestaurante(@Parameter(description = "ID do restaurante para buscar as reservas")
                                                                      @PathVariable String idRestaurante) {
        return ResponseEntity.ok(reservaService.buscarReservasPorRestaurante(idRestaurante));
    }

    @Operation(summary = "Busca reservas por status", description = "Retorna uma lista de reservas com o status especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas"),
            @ApiResponse(responseCode = "404", description = "Status não encontrado")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reserva>> buscarReservasPorStatus(@Parameter(description = "Status da reserva para filtrar")
                                                                 @PathVariable String status) {
        return ResponseEntity.ok(reservaService.buscarReservasPorStatus(status));
    }

    @Operation(summary = "Atualiza o status de uma reserva", description = "Altera o status de uma reserva existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status da reserva atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<Reserva> atualizarStatusReserva(@Parameter(description = "ID da reserva a ser atualizada") @PathVariable String id,
                                                          @Parameter(description = "Novo status da reserva") @RequestBody String status) {
        return ResponseEntity.ok(reservaService.atualizarStatusReserva(id, status.replace("\"", "")));
    }
}