# Microsserviços Java com Quarkus 
Este é um projeto de exemplo que demonstra a implementação de microsserviços usando Quarkus, focado nas entidades de Clientes, Pedidos e Produtos. Cada serviço é independente e se comunica através de APIs RESTful, simulando um ambiente de e-commerce distribuído, usando keycloak para implementar a autenticação de usuários no serviço order (pedidos).

## 🚀 Tecnologias Utilizadas
- Quarkus: Framework Java nativo para Kubernetes, otimizado para microsserviços.
- Java 17: Linguagem de programação.
- Maven: Ferramenta de automação de construção de projetos.
- Hibernate ORM com Panache: Simplifica o acesso a dados.
- KeyCloak para autenticação no order-service.
- PostgreSQL: Banco de dados relacional (pode ser configurado para outros SGBDs).
- RESTful Web Services: Comunicação entre os serviços.
- Docker: Para inicialização do banco de dados e do keycloak.

## ⚙️ Como Executar
Você tem algumas opções para executar os microsserviços:

1. Pré-requisitos
   
2. Certifique-se de ter instalado:

- JDK 17 ou superior
- Maven 3.8.x ou superior
- Docker

3. Executando o serviço do Keycloak e do banco de dados:
   
- na pasta raíz do projeto, dê o comando

```bash
# POSTGRESQL
------------

docker run -p 5432:5432 -e POSTGRES_PASSWORD=SUA-senha postgres.

#KEYCLOAK
------------
docker pull quay.io/keycloak/keycloak:17.0.0

docker run -d --name keycloak \
  --network keycloak-net \
  -p 8100:8080 \
  -p 8443:8443 \
  -e KC_DB=postgres \
  -e KC_DB_URL_HOST=keycloak-db \
  -e KC_DB_URL_DATABASE=keycloak \
  -e KC_DB_USERNAME=keycloak \
  -e KC_DB_PASSWORD=mysecretpassword \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  -e KC_HOSTNAME=localhost \
  quay.io/keycloak/keycloak:24.0.5 \
  start-dev
```

isso irá iniciar o dashboard do keycloak em **http://localhost:8100** e o postgres em **http://localhost:5432**.

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
- Cada serviço será iniciado em uma porta diferente do Swagger (padrão do Quarkus, ex: 8080, 8081, 8082, etc. ou configurado no application.properties). Verifique os logs para as portas exatas.


🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

📄 Licença
Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo LICENSE para mais detalhes.
