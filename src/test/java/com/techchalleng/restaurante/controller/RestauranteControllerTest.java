package com.techchalleng.restaurante.controller;

import com.techchalleng.restaurante.model.Restaurante;
import com.techchalleng.restaurante.service.RestauranteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class RestauranteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private RestauranteService restauranteService;

    @InjectMocks
    private RestauranteController restauranteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    @Test
    public void deveCadastrarRestaurante() throws Exception {
        String restauranteJson = "{\"nome\":\"Restaurante A\", \"localizacao\":\"Local A\", \"tipoCozinha\":\"Italiana\", \"horarioFuncionamento\":\"10:00-22:00\", \"capacidade\":50}";

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante A");
        restaurante.setLocalizacao("Local A");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento("10:00-22:00");
        restaurante.setCapacidade(50);

        when(restauranteService.cadastrarRestaurante(any(Restaurante.class))).thenReturn(restaurante);

        mockMvc.perform(post("/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restauranteJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deveBuscarRestaurantes() throws Exception {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante A");
        restaurante.setLocalizacao("Local A");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento("10:00-22:00");
        restaurante.setCapacidade(50);

        List<Restaurante> listaRestaurantes = Collections.singletonList(restaurante);

        when(restauranteService.buscarRestaurantes(anyString(), anyString(), anyString())).thenReturn(listaRestaurantes);

        mockMvc.perform(get("/restaurantes/buscar")
                        .param("nome", "Restaurante A")
                        .param("localizacao", "")
                        .param("tipoCozinha", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Restaurante A"))
                .andExpect(jsonPath("$[0].localizacao").value("Local A"));
    }
}