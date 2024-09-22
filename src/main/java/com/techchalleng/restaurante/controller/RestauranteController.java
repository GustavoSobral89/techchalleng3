package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Restaurante;
import com.techchalleng.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<Restaurante> cadastrarRestaurante(@RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(restauranteService.cadastrarRestaurante(restaurante));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Restaurante>> buscarRestaurantes(@RequestParam String nome,
                                                                @RequestParam String localizacao,
                                                                @RequestParam String tipoCozinha) {
        return ResponseEntity.ok(restauranteService.buscarRestaurantes(nome, localizacao, tipoCozinha));
    }
}