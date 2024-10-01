# Restaurante API

Esta é uma aplicação Spring Boot que fornece uma API para gerenciar restaurantes, reservas e avaliações.

## Tecnologias Usadas

- Java 17
- Spring Boot 3.3.4
- MongoDB
- Lombok
- Swagger para documentação da API

## Estrutura do Projeto

- **Controllers**
  - `AvaliacaoController`: Gerencia as avaliações de restaurantes.
  - `ReservaController`: Gerencia reservas de restaurantes.
  - `RestauranteController`: Gerencia restaurantes.

- **Services**
  - `AvaliacaoService`: Lógica de negócios para avaliações.
  - `ReservaService`: Lógica de negócios para reservas.
  - `RestauranteService`: Lógica de negócios para restaurantes.

- **Modelos**
  - `Avaliacao`: Representa uma avaliação de um restaurante.
  - `Reserva`: Representa uma reserva em um restaurante.
  - `Restaurante`: Representa um restaurante.

## Endpoints da API

### Avaliações

- **Criar Avaliação**
  - `POST /avaliacoes`
  
- **Buscar Avaliações por Restaurante**
  - `GET /avaliacoes/restaurante/{idRestaurante}`

### Reservas

- **Criar Reserva**
  - `POST /reservas`

- **Buscar Reservas por Restaurante**
  - `GET /reservas/restaurante/{idRestaurante}`

- **Buscar Reservas por Status**
  - `GET /reservas/status/{status}`

- **Atualizar Status de Reserva**
  - `PUT /reservas/{id}/status`

### Restaurantes

- **Cadastrar Restaurante**
  - `POST /restaurantes`

- **Buscar Restaurantes**
  - `GET /restaurantes/buscar?nome={nome}&localizacao={localizacao}&tipoCozinha={tipoCozinha}`

## Configuração do Ambiente

### Requisitos

- JDK 17
- Maven
- Docker (opcional, para uso em container)
- MongoDB

### Execução

Para executar a aplicação localmente, siga os passos abaixo:

Clone o repositório:
   ```bash
   git clone https://github.com/GustavoSobral89/techchalleng3.git
