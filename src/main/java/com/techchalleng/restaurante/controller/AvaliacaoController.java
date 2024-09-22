package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Avaliacao;
import com.techchalleng.restaurante.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoService.criarAvaliacao(avaliacao));
    }

    @GetMapping("/restaurante/{idRestaurante}")
    public ResponseEntity<List<Avaliacao>> buscarAvaliacoesPorRestaurante(@PathVariable String idRestaurante) {
        return ResponseEntity.ok(avaliacaoService.buscarAvaliacoesPorRestaurante(idRestaurante));
    }
}
