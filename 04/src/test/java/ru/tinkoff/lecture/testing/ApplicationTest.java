package ru.tinkoff.lecture.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest
class ApplicationTest {

    @SuppressWarnings("resource")
    private static final PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                    .withDatabaseName("postgres")
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withExposedPorts(5432);

    @BeforeAll
    static void setUp() {
        POSTGRES.start();
        System.setProperty(
                "spring.datasource.url",
                "jdbc:postgresql://%s:%s/postgres"
                        .formatted(POSTGRES.getHost(), POSTGRES.getMappedPort(5432)));
    }

    @Test
    void contextLoads() {
    }

}
