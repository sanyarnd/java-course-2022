package com.example.codefirst.demo.test.mockito;

import com.example.codefirst.demo.dto.response.GetAllUserResponse;
import com.example.codefirst.demo.mapping.TestMapperImpl;
import com.example.codefirst.demo.repository.UserRepository;
import com.example.codefirst.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.codefirst.demo.test.TestUserData.getStabUserList;
import static org.mockito.Mockito.*;

@SpringBootTest
class MockTest {


    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    public void setup() {

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
        verify(repository).getAllUsers();
        verify(repository, times(1)).getAllUsers();
//        verify(repository, times(2)).getAllUsers();  // TODO why 2?

//        verify(service).getAllUsers();  // TODO not a mock
    }

}
