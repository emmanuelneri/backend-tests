# backend-tests

----

Talk sobre testes no backend - #DevCia


Para rodar os testes de integração: `mvn clean install -Pintegration-test`

Para rodar a aplicação é necessário ter um serviço do Potgres em execução ou executar iniciar o Docker abaixo:
```
docker run -it \
    -e POSTGRES_DB=backendtests \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -p 5432:5432 \
   postgres:13-alpine
```