package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Reserva;
import com.techchalleng.restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.criarReserva(reserva));
    }

    @GetMapping("/restaurante/{idRestaurante}")
    public ResponseEntity<List<Reserva>> buscarReservasPorRestaurante(@PathVariable String idRestaurante) {
        return ResponseEntity.ok(reservaService.buscarReservasPorRestaurante(idRestaurante));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reserva>> buscarReservasPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(reservaService.buscarReservasPorStatus(status));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Reserva> atualizarStatusReserva(@PathVariable String id, @RequestBody String status) {
        return ResponseEntity.ok(reservaService.atualizarStatusReserva(id, status.replace("\"", "")));
    }
}