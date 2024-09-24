package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Avaliacao;
import com.techchalleng.restaurante.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @Operation(summary = "Cria uma nova avaliação", description = "Adiciona uma nova avaliação para um restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@Parameter(description = "Dados da avaliação a ser criada")
                                                    @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoService.criarAvaliacao(avaliacao));
    }

    @Operation(summary = "Busca avaliações por restaurante", description = "Retorna uma lista de avaliações para um restaurante específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliações encontradas"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    @GetMapping("/restaurante/{idRestaurante}")
    public ResponseEntity<List<Avaliacao>> buscarAvaliacoesPorRestaurante(@Parameter(description = "ID do restaurante para buscar as avaliações")
                                                                          @PathVariable String idRestaurante) {
        return ResponseEntity.ok(avaliacaoService.buscarAvaliacoesPorRestaurante(idRestaurante));
    }
}