package br.com.devcia.backendtests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
class AplicacaoITPackaged {

    private static final int APP_PORT = 8090;
    private static final int POSTGRES_PORT = 5432;

    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withBuild(true)
                    .withExposedService("app_1", APP_PORT, Wait.forListeningPort())
                    .withExposedService("postgres_1", POSTGRES_PORT, Wait.forListeningPort());

    @BeforeEach
    public void setUp() {
        environment.start();
    }

    @Test
    void deveRetornarAplicacaoHealthAposInicializacao() {
        final String url = String.format("http://localhost:%d/actuator/health", APP_PORT);

        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Health> resposta = restTemplate.getForEntity(url, Health.class);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());

        final Health health = resposta.getBody();
        Assertions.assertNotNull(health);
        Assertions.assertEquals(Status.UP, health.status());
    }

}