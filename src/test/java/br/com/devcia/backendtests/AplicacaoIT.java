package br.com.devcia.backendtests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.actuate.health.Status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
class AplicacaoIT {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    void deveRetornarAplicacaoHealthAposInicializacao() {
        final String url = String.format("http://localhost:%d/actuator/health", port);

        final RestTemplate restTemplate = restTemplateBuilder.build();
        final ResponseEntity<Health> resposta = restTemplate.getForEntity(url, Health.class);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());

        final Health health = resposta.getBody();
        Assertions.assertNotNull(health);
        Assertions.assertEquals(Status.UP, health.status());
    }

}