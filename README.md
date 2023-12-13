# CRUD Spring Boot

Este é um projeto Spring Boot que utiliza Thymeleaf para as páginas web, JPA para persistência no banco de dados e Spring Security para a segurança da aplicação.

## Configuração do Ambiente

Certifique-se de ter o Java JDK 17 ou 21.
Maven como gerenciador de dependencias.
Alguma IDE de sua escolha

## Configuração do Banco de Dados

Configure as propriedades do banco de dados no arquivo `application.properties` em src/main/resources:
*O projeto foi configurado para rodar no endereço 8082, não 8080 como padrão. Altere ou insira o link 'localhost:8082' ao rodar o projeto.

Altere o username, senha e url do banco
```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/projeto
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
server.port=8082
