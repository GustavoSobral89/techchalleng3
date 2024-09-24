package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Restaurante;
import com.techchalleng.restaurante.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @Operation(summary = "Cadastra um novo restaurante", description = "Adiciona um novo restaurante ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Restaurante> cadastrarRestaurante(@RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(restauranteService.cadastrarRestaurante(restaurante));
    }

    @Operation(summary = "Busca restaurantes", description = "Retorna uma lista de restaurantes com base nos parâmetros fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurantes encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum restaurante encontrado")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<Restaurante>> buscarRestaurantes(@RequestParam String nome,
                                                                @RequestParam String localizacao,
                                                                @RequestParam String tipoCozinha) {
        return ResponseEntity.ok(restauranteService.buscarRestaurantes(nome, localizacao, tipoCozinha));
    }
}