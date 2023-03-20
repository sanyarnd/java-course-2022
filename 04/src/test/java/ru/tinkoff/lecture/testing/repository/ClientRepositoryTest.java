package ru.tinkoff.lecture.testing.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import ru.tinkoff.lecture.testing.model.Client;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


//@DataJpaTest + @TestConfiguration + H2
@SpringBootTest
@DBRider
@DBUnit(leakHunter = true, caseSensitiveTableNames = true)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = { ClientRepositoryTest.DatabaseInitializer.class })
class ClientRepositoryTest {

    @SuppressWarnings("resource")
    private static final PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                    .withDatabaseName("postgres")
                    .withExposedPorts(5432);

    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll

    static void setUp() {
        POSTGRES.start();
    }

    @Test
    @Rollback
    @Transactional
    void repositoryTest1() {
        clientRepository.save(
                new Client()
                        .setFirstName("Sergey")
                        .setLastName("Khvatov"));

        clientRepository.save(
                new Client()
                        .setFirstName("Andrey")
                        .setLastName("Khvatov"));

        clientRepository.save(
                new Client()
                        .setFirstName("Evgeniy")
                        .setLastName("Shiryaev"));

        var rs = clientRepository.findAllByLastNameLike("Khv%", Pageable.ofSize(10));
        assertEquals(2, rs.size());
        assertContainsClient(rs, "Sergey", "Khvatov");
        assertContainsClient(rs, "Andrey", "Khvatov");
    }

    @Test
    @Rollback
    @Transactional
    void repositoryTest2() {
        clientRepository.save(
                new Client()
                        .setFirstName("Evgeniy")
                        .setLastName("Shiryaev"));

        var rs = clientRepository.findAllByLastNameLike("Khv%", Pageable.ofSize(10));
        assertTrue(rs.isEmpty());
    }

    @Test
    @Disabled
    @DataSet("clients.yaml")
    void repositoryTest3() {
        var rs = clientRepository.findAllByLastNameLike("Tet%", Pageable.ofSize(10));
        assertEquals(1, rs.size());
        assertContainsClient(rs, "Test", "Test");
    }

    private static void assertContainsClient(List<Client> rs, String firstName, String lastName) {
        assertTrue(
                rs.stream()
                        .anyMatch(it ->
                                Objects.equals(firstName, it.getFirstName())
                                        && Objects.equals(lastName, it.getLastName())));
    }

    static class DatabaseInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + POSTGRES.getJdbcUrl(),
                    "spring.datasource.username=" + POSTGRES.getUsername(),
                    "spring.datasource.password=" + POSTGRES.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }

    }

}
