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

    private static final int PORTA_APP = 8090;
    private static final int PORTA_POSTGRES = 5432;

    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withBuild(true)
                    .withExposedService("app_1", PORTA_APP, Wait.forListeningPort())
                    .withExposedService("postgres_1", PORTA_POSTGRES, Wait.forListeningPort());

    @BeforeEach
    public void setUp() {
        environment.start();
    }

    @Test
    void deveRetornarAplicacaoSaudavelAposInicializacao() {
        final String url = String.format("http://localhost:%d/actuator/health", PORTA_APP);

        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Health> resposta = restTemplate.getForEntity(url, Health.class);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());

        final Health health = resposta.getBody();
        Assertions.assertNotNull(health);
        Assertions.assertEquals(Status.UP, health.status());
    }

}