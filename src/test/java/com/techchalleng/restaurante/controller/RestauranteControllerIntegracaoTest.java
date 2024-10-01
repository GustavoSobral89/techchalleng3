package com.techchalleng.restaurante.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchalleng.restaurante.controller.RestauranteController;
import com.techchalleng.restaurante.model.Restaurante;
import com.techchalleng.restaurante.service.RestauranteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class RestauranteControllerIntegracaoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestauranteService restauranteService;

    @InjectMocks
    private RestauranteController restauranteController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void deveCadastrarRestauranteComSucesso() throws Exception {
        Restaurante restaurante = new Restaurante();
        restaurante.setId("12345");
        restaurante.setNome("Restaurante Exemplo");
        restaurante.setLocalizacao("Rua das Flores, 123");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento("Seg a Sex: 11h - 23h");
        restaurante.setCapacidade(100);

        when(restauranteService.cadastrarRestaurante(any(Restaurante.class))).thenReturn(restaurante);

        mockMvc.perform(post("/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurante)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(restaurante)));

        verify(restauranteService, times(1)).cadastrarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveBuscarRestaurantesComSucesso() throws Exception {
        Restaurante restaurante1 = new Restaurante();
        restaurante1.setId("12345");
        restaurante1.setNome("Restaurante Exemplo");
        restaurante1.setLocalizacao("Rua das Flores, 123");
        restaurante1.setTipoCozinha("Italiana");
        restaurante1.setHorarioFuncionamento("Seg a Sex: 11h - 23h");
        restaurante1.setCapacidade(100);

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setId("67890");
        restaurante2.setNome("Outro Restaurante");
        restaurante2.setLocalizacao("Avenida Central, 456");
        restaurante2.setTipoCozinha("Chinesa");
        restaurante2.setHorarioFuncionamento("Seg a Dom: 10h - 22h");
        restaurante2.setCapacidade(80);

        List<Restaurante> listaRestaurantes = Arrays.asList(restaurante1, restaurante2);
        when(restauranteService.buscarRestaurantes("Restaurante", "Rua", "Italiana"))
                .thenReturn(listaRestaurantes);

        mockMvc.perform(get("/restaurantes/buscar")
                        .param("nome", "Restaurante")
                        .param("localizacao", "Rua")
                        .param("tipoCozinha", "Italiana"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(listaRestaurantes)));

        verify(restauranteService, times(1)).buscarRestaurantes("Restaurante", "Rua", "Italiana");
    }
}
