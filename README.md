# Microsserviços Quarkus 
Este é um projeto de exemplo que demonstra a implementação de microsserviços usando Quarkus, focado nas entidades de Clientes, Pedidos e Produtos. Cada serviço é independente e se comunica através de APIs RESTful, simulando um ambiente de e-commerce distribuído.

## 🚀 Tecnologias Utilizadas
- Quarkus: Framework Java nativo para Kubernetes, otimizado para microsserviços.
- Java 17: Linguagem de programação.
- Maven: Ferramenta de automação de construção de projetos.
- Hibernate ORM com Panache: Simplifica o acesso a dados.
- PostgreSQL: Banco de dados relacional (pode ser configurado para outros SGBDs).
- RESTful Web Services: Comunicação entre os serviços.
- Docker Compose: Para orquestração de múltiplos contêineres (opcional).

## ⚙️ Como Executar
Você tem algumas opções para executar os microsserviços:

1. Pré-requisitos
2. Certifique-se de ter instalado:

- JDK 17 ou superior
- Maven 3.8.x ou superior
- Docker

3. Executando os Serviços no Docker:
- na pasta raíz do projeto, dê o comando

```bash
docker compose up
```

isso irá criar os contêineres das aplicações e dos bancos.

4. Executando os Serviços Individualmente (Modo Desenvolvimento)
Você pode iniciar cada serviço em modo de desenvolvimento, o que permite o hot-reload de código. Abra um terminal separado para cada serviço:

# No diretório customer-service

```bash
cd customer-service
mvn quarkus:dev
```

# No diretório product-service

```bash
cd product-service
mvn quarkus:dev
```

# No diretório order-service

```bash
cd order-service
mvn quarkus:dev
```
- Cada serviço será iniciado em uma porta diferente (padrão do Quarkus, ex: 8080, 8081, 8082, etc. ou configurado no application.properties). Verifique os logs para as portas exatas.

### 5. Construindo e Executando como JARS Executáveis

# A partir do diretório raiz dos projetos

```bash
mvn clean package
```

🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

📄 Licença
Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo LICENSE para mais detalhes.
