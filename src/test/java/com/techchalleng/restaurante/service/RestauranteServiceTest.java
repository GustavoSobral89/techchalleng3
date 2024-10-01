package com.techchalleng.restaurante.service;

import com.techchalleng.restaurante.model.Restaurante;
import com.techchalleng.restaurante.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestauranteServiceTest {
    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteService restauranteService;

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurante = new Restaurante();
        restaurante.setNome("Restaurante A");
        restaurante.setLocalizacao("Local A");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento("10:00-22:00");
        restaurante.setCapacidade(50);
    }

    @Test
    public void deveCadastrarRestaurante() {
        when(restauranteRepository.save(restaurante)).thenReturn(restaurante);
        Restaurante resultado = restauranteService.cadastrarRestaurante(restaurante);
        assertEquals(restaurante.getNome(), resultado.getNome());
        verify(restauranteRepository, times(1)).save(restaurante);
    }

    @Test
    public void deveBuscarRestaurantesPorNome() {
        when(restauranteRepository.findByNomeContainingOrLocalizacaoContainingOrTipoCozinhaContaining("Restaurante A", "", ""))
                .thenReturn(Collections.singletonList(restaurante));

        List<Restaurante> resultado = restauranteService.buscarRestaurantes("Restaurante A", "", "");
        assertEquals(1, resultado.size());
        assertEquals(restaurante.getNome(), resultado.get(0).getNome());
    }
}