package com.example.codefirst.demo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class CreateUserRequst {

    @NotNull
    private String firstName;
    @NotEmpty
    private String lastName;
    private String middleName;
    private String password;

}
