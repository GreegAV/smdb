package com.levi9.smdb.integration.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ActiveProfiles("test")
public class BaseIntegrationTest {

    private static final String DOCKER_IMAGE_NAME = "postgres:14.7-alpine";
    private static final String DATABASE_PASSWORD = "smdb_test";
    private static final String DATABASE_USERNAME = "smdb_test";
    private static final String DATABASE_NAME = "smdb_test";

    public static final PostgreSQLContainer postgresqlContainer;

    static {
        postgresqlContainer = new PostgreSQLContainer(DOCKER_IMAGE_NAME).withDatabaseName(DATABASE_NAME).withPassword(DATABASE_PASSWORD)
                .withUsername(DATABASE_USERNAME);
        postgresqlContainer.start();
    }

    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Test
    void test() {
        Assertions.assertTrue(postgresqlContainer.isRunning());
    }
}