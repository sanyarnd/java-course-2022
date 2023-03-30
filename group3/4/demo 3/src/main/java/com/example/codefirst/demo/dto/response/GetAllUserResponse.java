package com.example.codefirst.demo.dto.response;

import com.example.codefirst.demo.dto.UserDto;
import com.example.codefirst.demo.model.User;
import lombok.Data;

import java.util.List;

@Data
public class GetAllUserResponse {

    private List<UserDto> users;

}
