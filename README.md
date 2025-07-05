# delivery-api-karin

API de Delivery - Projeto de Desenvolvimento

# Delivery Tech API

Sistema de delivery desenvolvido com Spring Boot e Java 21.

# Estrutura das pastas

```
./
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── deliverytech/
    │   │           ├── delivery/
    │   │           │   ├── DeliveryApiApplication.java
    │   │           │   ├── config/
    │   │           │   │   └── ModelMapperConfig.java
    │   │           │   ├── controller/
    │   │           │   │   ├── ClienteController.java
    │   │           │   │   ├── HealthController.java
    │   │           │   │   ├── HelloController.java
    │   │           │   │   ├── ItemPedidoController.java
    │   │           │   │   ├── PedidoController.java
    │   │           │   │   └── RestauranteController.java
    │   │           │   ├── dto/
    │   │           │   │   ├── ClienteDTO.java
    │   │           │   │   ├── ClienteResponseDTO.java
    │   │           │   │   ├── ItemPedidoDTO.java
    │   │           │   │   ├── ItemPedidoResponseDTO.java
    │   │           │   │   ├── PedidoCreationDTO.java
    │   │           │   │   ├── PedidoDTO.java
    │   │           │   │   ├── PedidoResponseDTO.java
    │   │           │   │   ├── RestauranteDTO.java
    │   │           │   │   └── RestauranteResponseDTO.java
    │   │           │   ├── entity/
    │   │           │   │   ├── Cliente.java
    │   │           │   │   ├── ItemPedido.java
    │   │           │   │   ├── Pedido.java
    │   │           │   │   ├── Produto.java
    │   │           │   │   └── Restaurante.java
    │   │           │   ├── enums/
    │   │           │   │   └── StatusPedido.java
    │   │           │   ├── exception/
    │   │           │   │   ├── BusinessException.java
    │   │           │   │   ├── EntityNotFoundException.java
    │   │           │   │   └── GlobalExceptionHandler.java
    │   │           │   ├── repository/
    │   │           │   │   ├── ClienteRepository.java
    │   │           │   │   ├── ItemPedidoRepository.java
    │   │           │   │   ├── PedidoRepository.java
    │   │           │   │   ├── ProdutoRepository.java
    │   │           │   │   └── RestauranteRepository.java
    │   │           │   └── service/
    │   │           │       ├── ClienteService.java
    │   │           │       ├── ClienteServiceImpl.java
    │   │           │       ├── ItemPedidoService.java
    │   │           │       ├── PedidoService.java
    │   │           │       ├── PedidoServiceImpl.java
    │   │           │       ├── ProdutoService.java
    │   │           │       └── RestauranteService.java
    │   │           └── mapper/
    │   │               └── PedidoMapper.java
    │   └── resources/
    │       ├── application.properties
    │       ├── data.sql
    │       └── schema.sql
    └── test/
        └── java/
            └── com/
                └── deliverytech/
                    └── delivery/
                        └── DeliveryApiApplicationTests.java
```

API documentation.postman_collection.json

## 🚀 Tecnologias

- **Java 21 LTS** (versão mais recente)
- Spring Boot 3.2.x
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## ⚡Recursos Modernos Utilizados

- Records (Java 14+)
- Text Blocks (Java 15+)
- Pattern Matching (Java 17+)
- Virtual Threads (Java 21)

## 🏃Como executar

1. **Pré-requisitos:** JDK 21 instalado
2. Clone o repositório
3. Execute: './mvnw spring-boot:run' (no linux) ou 'mvn spring-boot:run (no windows)
4. Acesse: http://localhost:8080/health

##🗒️Endpoints

- GET /health - Status da aplicação (inclui versão Java)
- GET /info - Informações da aplicação
- GET /h2-console - Console do banco H2

## ⚙️Configuração

- Porta: 8080
- Banco: H2 em memória
- Profile: development

## 👩‍💻 Desenvolvedor

[Karin] - [SDE-TI13]
Desenvolvido com JDK 21 e Spring Boot 3.2.x
