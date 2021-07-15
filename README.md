# backend-tests

----

Talk sobre testes no backend - #DevCia

### Pré requisitos
- Docker
- Java 16+
- Maven 3+

### Execução dos testes
- Para execução dos testes unitários: `mvn clean test`
- Para execução dos testes de integração: `mvn clean test -Pintegration-test`
- Para execução dos testes na imagem Docker: `mvn clean verify packaged-integration-test`

### Execução da aplicação

A aplicação tem dependência do Postgres, com isso o comando abaixo pode ser utilizado para inicializar o Postgres no Docker.
```
docker run -it \
    -e POSTGRES_DB=backendtests \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -p 5432:5432 \
   postgres:13-alpine
```

E executar o comando seguinte para inicializar a aplicação: `mvn spring-boot:run`.