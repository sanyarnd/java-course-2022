package com.example.codefirst.demo.test.mockito;

import com.example.codefirst.demo.common.TestClass;
import com.example.codefirst.demo.dto.request.CreateUserRequst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MockVsSpyTest {

    @Spy  // TODO
    private TestClass testClass;

    @BeforeEach
    public void setup() {
        Mockito.when(testClass.getSecond()).thenReturn("Second MOCK!!!");
    }

    @Test
    public void secondMockTest() {
        // given

        // when
        String response = testClass.getSecond();

        // then
        assertThat(response, is(not(isEmptyOrNullString())));
        assertThat(response, is(equalTo("Second MOCK!!!")));
    }

    @Test
    public void thirdMockTest() {
        // given
        CreateUserRequst requst = new CreateUserRequst()
                .setFirstName(null)
                .setLastName("lastName")
                .setMiddleName("MiddleName");

        // when
        String response = testClass.getThird();

        // then
        assertThat(response, is(not(isEmptyOrNullString())));
        assertThat(response, is(equalTo("Third!")));
    }
//
    @Test
    public void fourthMockTest() {
        // given

        // when
        String response = testClass.getSecond();

        // then
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> testClass.getFourth(),
                "Expected getFourth() to throw, but it didn't"
        );

        assertThat(thrown, is(notNullValue()));
    }

}
