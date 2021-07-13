package br.com.devcia.backendtests;

import org.junit.Rule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String POSTGRES_DOCKER_IMAGE = "postgres:13-alpine";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DATABASE_NAME = "integrationtest";

    @Rule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(POSTGRES_DOCKER_IMAGE)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USER)
            .withPassword(PASSWORD);

    public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
        postgreSQLContainer.start();
        TestPropertyValues.of(
                "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                "spring.datasource.password=" + postgreSQLContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }

}
