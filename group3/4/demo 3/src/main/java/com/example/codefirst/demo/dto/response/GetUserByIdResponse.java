package com.example.codefirst.demo.dto.response;

import com.example.codefirst.demo.model.User;
import lombok.Data;

@Data
public class GetUserByIdResponse {

    private User user;
}
