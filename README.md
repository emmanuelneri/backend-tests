```
docker run -it \
    -e POSTGRES_DB=backendtests \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -p 5432:5432 \
   postgres:13-alpine
```