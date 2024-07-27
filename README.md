# Desafio Backend: Picpay Simplificado 💰

![Java](https://img.shields.io/badge/java-FF5722.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-F57F17?style=for-the-badge&logo=Hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-003B6F?style=for-the-badge&logo=postgresql&logoColor=white)
![PgAdmin](https://img.shields.io/badge/PgAdmin-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

<p align="left" width="100%">
    <img width="30%" src="https://github.com/buildrun-tech/buildrun-desafio-backend-picpay/blob/main/images/picpay-logo.jpg"> 
</p>

Essa é a minha proposta de solução para o desafio popular da entrevista da vaga de desenvolvedor do Picpay.

Em resumo, o desafio técnico proposto envolve o desenvolvimento de uma aplicação que simula um sistema simplificado de transferência de dinheiro entre dois tipos de usuários: comuns e lojistas.

## O que é o desafio? 🤔

O desafio encontrado no GitHub do PicPay, originalmente parte do processo de entrevista para a vaga de analista na empresa, ganhou popularidade na internet.

Desde então, sua resolução se tornou uma excelente maneira de aplicar conceitos de API Rest e explorar tecnologias backend. Por isso, decidi resolvê-lo para treinar minhas habilidades.

O desafio pode ser encontrado aqui: <https://github.com/PicPay/picpay-desafio-backend>

## Requisitos da Aplicação ✅

Esses foram os requisitos definidos no enunciado original.

### Usuários

* Existem dois tipos de usuário: comum e lojista.
* Usuário deve ter **nome completo**, **CPF**, **e-mail** e **senha** para ambos os tipos de usuários (comuns e lojistas).
* Os campos CPF/CNPJ e e-mail devem ser únicos.

### Transferências de Dinheiro

* Usuários comuns podem enviar dinheiro para outros usuários comuns e lojistas.
* Lojistas apenas recebem transferências e não podem enviar dinheiro.
* É necessário validar o saldo do usuário antes da transferência.

### Autorização de Transferência

* Consultar um mock de serviço autorizador externo antes de finalizar a transferência.
Link: <https://run.mocky.io/v3/43aa52dc-7d40-4246-a1e7-8bafef37d317>

> **Observação:** O serviço se encontra indisponível atualmente. Foi necessário criar um mock novo no mock.io. O mock retorna status http 200.

### Notificação de Pagamento

* Usar um mock de serviço de notificação externo para simular o envio de notificações para usuário ou lojista que recebeu o pagamento.
Link: <https://run.mocky.io/v3/d7a00f28-b4e5-44d5-9c66-2eb8e290d216>

> **Observação:** O serviço se encontra indisponível atualmente. Foi necessário criar um mock novo no mock.io. O mock retorna status http 200.

### Serviço RESTful 🚀

* Desenvolvimento de um serviço RESTful para toda a aplicação.

## Tecnologias 💻
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Lombok](https://projectlombok.org/)
- [H2](https://www.baeldung.com/spring-boot-h2-database)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [PgAdmin](https://www.pgadmin.org/)
- [JUnit5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [MockMvc](https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework.html)
- [Jacoco](https://www.eclemma.org/jacoco/)
- [Bean Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- [HATEOAS](https://spring.io/projects/spring-hateoas)

## Práticas adotadas ✨

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Testes Automatizados
- Tratamento de Exceções Personalizada
- Geração automática do Swagger com a OpenAPI 3

## Diferenciais 🔥

Alguns diferenciais que não foram solicitados no desafio:

* Utilização de Docker
* TDD-Test Driven Development
* Tratamento de exceções
* Validações com Constraints Customizados
* Testes Unitários e de Integração
* Cobertura de Testes com Jacoco
* Documentação Swagger
* Criação de novos mocks
* Implementação de HATEOAS

## Como executar 🎉

1.Clonar repositório git:

```text
git clone https://github.com/FernandoCanabarroAhnert/picpay-desafio-backend.git
```

2.Instalar dependências.

```text
mvn clean install
```

3.Executar a aplicação Spring Boot.

4.Testar endpoints através do Postman ou da url
<http://localhost:8080/swagger-ui/index.html#/>

### Usando Docker 🐳

- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Construir a imagem:
```
./mvnw spring-boot:build-image
```
- Executar o container:
```
docker run --name desafio-picpay -p 8080:8080  -d desafio-picpay:0.0.1-SNAPSHOT
```

## API Endpoints 📚

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/):

- Criar Wallet 
```
$ http POST http://localhost:8080/wallets

{
    "fullName": "John",
    "cpfCnpj": "10123498765",
    "email": "john@gmail.com",
    "password": "123456",
    "walletType": "USER"
}
```

- Listar Wallets
```
$ http GET http://localhost:8080/wallets

[
       {
            "id": 1,
            "fullName": "Maria",
            "cpfCnpj": "12345678910",
            "email": "maria@gmail.com",
            "password": "123456",
            "balance": 100.0,
            "walletType": "USER",
            "links": [
                {
                    "rel": "self",
                    "href": "http://localhost:8080/wallets"
                }
            ]
        },
        {
            "id": 2,
            "fullName": "Alex",
            "cpfCnpj": "01987654321",
            "email": "alex@gmail.com",
            "password": "123456",
            "balance": 70.0,
            "walletType": "MERCHANT",
            "links": [
                {
                    "rel": "self",
                    "href": "http://localhost:8080/wallets"
                }
            ]
        }
]
```
- Criar Transação 
```
$ http POST http://localhost:8080/transfer

{
    "payerId": 1,
    "payeeId": 2,
    "value": 50
}

```