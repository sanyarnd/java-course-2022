package com.example.codefirst.demo.controller;

import com.example.codefirst.demo.dto.request.CreateUserRequst;
import com.example.codefirst.demo.dto.response.CreateUserResponse;
import com.example.codefirst.demo.dto.response.GetAllUserResponse;
import com.example.codefirst.demo.dto.response.GetUserByIdResponse;
import com.example.codefirst.demo.model.User;
import com.example.codefirst.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public GetAllUserResponse getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public GetUserByIdResponse getUserById(@PathVariable("userId") String userId) {
        throw new NotImplementedException();
    }

    @PostMapping
    public CreateUserResponse createUser(CreateUserRequst requst) {
        throw new NotImplementedException();
    }

    @PutMapping("/{userId}")
    public User updateUser(User user) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        throw new NotImplementedException();
    }

}
