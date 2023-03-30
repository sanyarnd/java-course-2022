package com.example.codefirst.demo.mapping;

import com.example.codefirst.demo.client.dto.Hello;
import com.example.codefirst.demo.dto.UserDto;
import com.example.codefirst.demo.dto.response.GetAllUserResponse;
import com.example.codefirst.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TestMapper {

    List<UserDto> toGetAllUserResponse(List<User> source);

    UserDto toUserDto(User source);

}
