# delivery-api-karin

API de Delivery - Projeto de Desenvolvimento

# Delivery Tech API

Sistema de delivery desenvolvido com Spring Boot e Java 21.

# Estrutura

```
delivery-api
│ .gitignore
│ README.md
│
├───delivery-api
│ │ .gitattributes
│ │ .gitignore
│ │ HELP.md
│ │ mvnw
│ │ mvnw.cmd
│ │ pom.xml
│ │
│ ├───.mvn
│ │ └───wrapper
│ │ maven-wrapper.properties
│ │
│ ├───.vscode
│ │ settings.json
│ │
│ ├───src
│ │ ├───main
│ │ │ ├───java
│ │ │ │ └───com
│ │ │ │ └───deliverytech
│ │ │ │ ├───delivery
│ │ │ │ │ │ DeliveryApiApplication.java
│ │ │ │ │ │
│ │ │ │ │ ├───config
│ │ │ │ │ │ ModelMapperConfig.java
│ │ │ │ │ │
│ │ │ │ │ ├───controller
│ │ │ │ │ │ ClienteController.java
│ │ │ │ │ │ HealthController.java
│ │ │ │ │ │ HelloController.java
│ │ │ │ │ │ ItemPedidoController.java
│ │ │ │ │ │ PedidoController.java
│ │ │ │ │ │ RestauranteController.java
│ │ │ │ │ │
│ │ │ │ │ ├───dto
│ │ │ │ │ │ ClienteDTO.java
│ │ │ │ │ │ ClienteResponseDTO.java
│ │ │ │ │ │ ItemPedidoDTO.java
│ │ │ │ │ │ ItemPedidoResponseDTO.java
│ │ │ │ │ │ PedidoCreationDTO.java
│ │ │ │ │ │ PedidoDTO.java
│ │ │ │ │ │ PedidoResponseDTO.java
│ │ │ │ │ │ RestauranteDTO.java
│ │ │ │ │ │ RestauranteResponseDTO.java
│ │ │ │ │ │
│ │ │ │ │ ├───entity
│ │ │ │ │ │ Cliente.java
│ │ │ │ │ │ ItemPedido.java
│ │ │ │ │ │ Pedido.java
│ │ │ │ │ │ Produto.java
│ │ │ │ │ │ Restaurante.java
│ │ │ │ │ │
│ │ │ │ │ ├───enums
│ │ │ │ │ │ StatusPedido.java
│ │ │ │ │ │
│ │ │ │ │ ├───exception
│ │ │ │ │ │ BusinessException.java
│ │ │ │ │ │ EntityNotFoundException.java
│ │ │ │ │ │ GlobalExceptionHandler.java
│ │ │ │ │ │
│ │ │ │ │ ├───repository
│ │ │ │ │ │ ClienteRepository.java
│ │ │ │ │ │ ItemPedidoRepository.java
│ │ │ │ │ │ PedidoRepository.java
│ │ │ │ │ │ ProdutoRepository.java
│ │ │ │ │ │ RestauranteRepository.java
│ │ │ │ │ │
│ │ │ │ │ └───service
│ │ │ │ │ ClienteService.java
│ │ │ │ │ ClienteServiceImpl.java
│ │ │ │ │ ItemPedidoService.java
│ │ │ │ │ PedidoService.java
│ │ │ │ │ PedidoServiceImpl.java
│ │ │ │ │ ProdutoService.java
│ │ │ │ │ RestauranteService.java
│ │ │ │ │
│ │ │ │ └───mapper
│ │ │ │ PedidoMapper.java
│ │ │ │
│ │ │ └───resources
│ │ │ │ application.properties
│ │ │ │ data.sql
│ │ │ │ schema.sql
│ │ │ │
│ │ │ ├───static
│ │ │ └───templates
│ │ └───test
│ │ └───java
│ │ └───com
│ │ └───deliverytech
│ │ └───delivery
│ │ DeliveryApiApplicationTests.java
│ │
│ └───target
│ │ delivery-api-0.0.1-SNAPSHOT.jar
│ │ delivery-api-0.0.1-SNAPSHOT.jar.original
│ │
│ ├───classes
│ │ │ application.properties
│ │ │ data.sql
│ │ │ schema.sql
│ │ │
│ │ └───com
│ │ └───deliverytech
│ │ ├───delivery
│ │ │ │ DeliveryApiApplication.class
│ │ │ │
│ │ │ ├───config
│ │ │ │ ModelMapperConfig.class
│ │ │ │
│ │ │ ├───controller
│ │ │ │ ClienteController.class
│ │ │ │ HealthController$AppInfo.class
│       │           │   │       HealthController.class
│       │           │   │       HelloController.class
│       │           │   │       ItemPedidoController.class
│       │           │   │       PedidoController.class
│       │           │   │       RestauranteController.class
│       │           │   │
│       │           │   ├───dto
│       │           │   │       ClienteDTO.class
│       │           │   │       ClienteResponseDTO.class
│       │           │   │       ItemPedidoDTO.class
│       │           │   │       ItemPedidoResponseDTO.class
│       │           │   │       PedidoCreationDTO.class
│       │           │   │       PedidoDTO.class
│       │           │   │       PedidoResponseDTO.class
│       │           │   │       RestauranteDTO.class
│       │           │   │       RestauranteResponseDTO.class
│       │           │   │
│       │           │   ├───entity
│       │           │   │       Cliente.class
│       │           │   │       ItemPedido.class
│       │           │   │       Pedido.class
│       │           │   │       Produto.class
│       │           │   │       Restaurante.class
│       │           │   │
│       │           │   ├───enums
│       │           │   │       StatusPedido.class
│       │           │   │
│       │           │   ├───exception
│       │           │   │       BusinessException.class
│       │           │   │       EntityNotFoundException.class
│       │           │   │       GlobalExceptionHandler.class
│       │           │   │
│       │           │   ├───repository
│       │           │   │       ClienteRepository.class
│       │           │   │       ItemPedidoRepository.class
│       │           │   │       PedidoRepository.class
│       │           │   │       ProdutoRepository.class
│       │           │   │       RestauranteRepository.class
│       │           │   │
│       │           │   └───service
│       │           │           ClienteService.class
│       │           │           ClienteServiceImpl$ClienteService.class
│ │ │ ClienteServiceImpl.class
│ │ │ ItemPedidoService.class
│ │ │ PedidoService.class
│ │ │ PedidoServiceImpl.class
│ │ │ ProdutoService.class
│ │ │ RestauranteService.class
│ │ │
│ │ └───mapper
│ │ PedidoMapper.class
│ │
│ ├───generated-sources
│ │ └───annotations
│ ├───generated-test-sources
│ │ └───test-annotations
│ ├───maven-archiver
│ │ pom.properties
│ │
│ ├───maven-status
│ │ └───maven-compiler-plugin
│ │ ├───compile
│ │ │ └───default-compile
│ │ │ createdFiles.lst
│ │ │ inputFiles.lst
│ │ │
│ │ └───testCompile
│ │ └───default-testCompile
│ │ createdFiles.lst
│ │ inputFiles.lst
│ │
│ ├───surefire-reports
│ │ com.deliverytech.delivery.DeliveryApiApplicationTests.txt
│ │ TEST-com.deliverytech.delivery.DeliveryApiApplicationTests.xml
│ │
│ └───test-classes
│ └───com
│ └───deliverytech
│ └───delivery
│ DeliveryApiApplicationTests.class
│
└───Entregaveis
├───AtividadePratica01
│ aplicacao-funcionando.png
│ commit-inicial.png
│ controller-simples.png
│ delivery-api.zip
│ endpoint-health-info.png
│ estrutura-pastas.png
│ extension-java.png
│ git-version.png
│ github.txt
│ h2-console.png
│ healthController.png
│ java-version.png
│ readme-basico.png
│ spring-boot.png
│
└───AtividadePratica02
  API documentation.postman_collection.json
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
