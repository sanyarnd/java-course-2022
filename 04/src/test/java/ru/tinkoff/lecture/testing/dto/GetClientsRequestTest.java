package ru.tinkoff.lecture.testing.dto;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.tinkoff.lecture.testing.ext.LoggingExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(LoggingExtension.class) // другие общеиспользуемые примеры - MockitoExtension, SpringExtension
class GetClientsRequestTest {

    private static final String SORT = "firstName,desc";
    private static final String FIRST_NAME_PROPERTY = "firstName";
    private static final String LASTNAME_PATTERN = "Khva";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Время начала всех тестов - " + LocalDateTime.now());
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("\tВремя начала теста - " + LocalDateTime.now());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Время завершения всех тестов - " + LocalDateTime.now());
    }

    @AfterEach
    void afterEach() {
        System.out.println("\tВремя завершения теста - " + LocalDateTime.now());
    }

    @Test
    void singlePageTest() {
        // Arrange / Setup
        var request = new GetClientsRequest(10, 0, SORT, LASTNAME_PATTERN);

        // Act
        var page = request.page();

        // Assert
        assertPage(page, 0, 10, 0, 0);
        assertSort(page.getSort());
    }

    @Test
    void pageWithWasteTest() {
        // Arrange / Setup
        var request = new GetClientsRequest(135, 1030, SORT, LASTNAME_PATTERN);

        // Act
        var page = request.page();

        // Assert
        assertPage(page, 7, 146, 8, 3);
        assertSort(page.getSort());
    }

    private static void assertPage(
            Pageable page,
            int expectedPage, int expectedPageSize,
            int expectedHeadWaste, int expectedTailWaste) {

        if (page instanceof GetClientsRequest.WastedPageRequest wastedPageRequest) {
            assertEquals(expectedPage, wastedPageRequest.getPageNumber());
            assertEquals(expectedPageSize, wastedPageRequest.getPageSize());
            assertEquals(expectedHeadWaste, wastedPageRequest.getHeadWaste());
            assertEquals(expectedTailWaste, wastedPageRequest.getTailWaste());
        } else {
            fail("Ожидался объект класса WastedPageRequest");
        }
    }

    private static void assertSort(Sort sort) {
        assertTrue(sort.isSorted());
        var order = sort.getOrderFor(FIRST_NAME_PROPERTY);
        assertNotNull(order);
        assertEquals(FIRST_NAME_PROPERTY, order.getProperty());
        assertEquals(Sort.Direction.DESC, order.getDirection());
    }

}
