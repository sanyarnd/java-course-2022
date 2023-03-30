package com.example.codefirst.demo.service;

import com.example.codefirst.demo.dto.UserDto;
import com.example.codefirst.demo.dto.response.GetAllUserResponse;
import com.example.codefirst.demo.mapping.TestMapper;
import com.example.codefirst.demo.model.User;
import com.example.codefirst.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final TestMapper mapper;


    public GetAllUserResponse getAllUsers() {

        List<User> userList = repository.getAllUsers();

        if (userList.size() > 20) {
            var unusedList = repository.getAllUsers();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(unusedList);
        }

        List<UserDto> respone = mapper.toGetAllUserResponse(userList);
        return new GetAllUserResponse().setUsers(respone);
    }

}
