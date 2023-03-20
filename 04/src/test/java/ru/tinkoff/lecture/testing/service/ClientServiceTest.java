package ru.tinkoff.lecture.testing.service;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.tinkoff.lecture.testing.dto.GetClientsRequest;
import ru.tinkoff.lecture.testing.model.Client;
import ru.tinkoff.lecture.testing.repository.ClientRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ExtendWith(RandomBeansExtension.class)
class ClientServiceTest {

    private static final String LASTNAME = "Khv";

    @Mock // Mockito.mock()
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Random
    private static Client RANDOM_CLIENT_1;

    @Random
    private static Client RANDOM_CLIENT_2;

    @BeforeEach
    void setupClients() {
        RANDOM_CLIENT_1.setLastName("Khvatov");
        RANDOM_CLIENT_2.setLastName("Khvativ");
    }

    @Test
    void getClientsTest() {
        when(clientRepository.findAllByLastNameLike(eq(LASTNAME), any()))
                .thenReturn(List.of(RANDOM_CLIENT_1, RANDOM_CLIENT_2));

        var rq = new GetClientsRequest(10, 0, "firstName,asc", LASTNAME);

        var result = clientService.getClients(rq);

        assertEquals(2, result.size());
        assertTrue(result.contains(RANDOM_CLIENT_1));
        assertTrue(result.contains(RANDOM_CLIENT_2));

        var pageCaptor = ArgumentCaptor.forClass(Pageable.class); // @ArgumentCaptor
        verify(clientRepository, times(1)).findAllByLastNameLike(eq(LASTNAME), pageCaptor.capture());

        var page = pageCaptor.getValue();
        assertEquals(0, page.getPageNumber());
        assertEquals(10, page.getPageSize());

        var order = page.getSort().getOrderFor("firstName");
        assertNotNull(order);
        assertEquals("firstName", order.getProperty());
        assertEquals(Sort.Direction.ASC, order.getDirection());
    }

}
