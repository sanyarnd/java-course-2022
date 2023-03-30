package com.example.codefirst.demo.test.mockito;

import com.example.codefirst.demo.dto.response.GetAllUserResponse;
import com.example.codefirst.demo.mapping.TestMapperImpl;
import com.example.codefirst.demo.repository.UserRepository;
import com.example.codefirst.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.codefirst.demo.test.TestUserData.getStabUserList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MockCodeTest {

    private UserService service;

//    @Mock
    private UserRepository repository;

    @BeforeEach
    public void setup() {
        this.repository = Mockito.mock(UserRepository.class);
        this.service = new UserService(repository, new TestMapperImpl());

    }

    @Test
    void shouldMapUser() {
        // given
        when(repository.getAllUsers()).
                thenReturn(getStabUserList());

        // when
        GetAllUserResponse response = service.getAllUsers();


        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getUsers(), is(notNullValue()));
        assertThat(response.getUsers(), hasSize(2));

        var user1 = response.getUsers().get(0);
        assertThat(user1, is(notNullValue()));
        assertThat(user1.getFirstName(), is(not(isEmptyOrNullString())));
        assertThat(user1.getFirstName(), equalTo("test1"));
        assertThat(user1.getLastName(), equalTo("test1"));
        assertThat(user1.getMiddleName(), equalTo("test1"));

        var user2 = response.getUsers().get(1);
        assertThat(user2, is(notNullValue()));
        assertThat(user2.getFirstName(), is(not(isEmptyOrNullString())));
        assertThat(user2.getFirstName(), equalTo("test2"));
        assertThat(user2.getLastName(), is(isEmptyOrNullString()));
        assertThat(user2.getMiddleName(), is(isEmptyString()));

    }

}
