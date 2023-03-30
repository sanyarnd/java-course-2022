package com.example.codefirst.demo.repository;

import com.example.codefirst.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList;

    public UserRepository() {
        List<User> users = new ArrayList<>();
        users.add(createUser("name1", "surname1", "patronymic1", "qwe"));
        users.add(createUser("name2", "surname2", "patronymic2", "asd"));
        users.add(createUser("name3", "surname3", "patronymic3", "zxv"));
        users.add(createUser("name4", "surname4", "patronymic4", "gsd"));
        users.add(createUser("name5", "surname5", "patronymic5", "wey"));

        this.userList = users;
    }

    private User createUser(String firstName, String lastName, String middleName, String password) {
        return new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMiddleName(middleName)
                .setPassword(password);
    }

    public List<User> getAllUsers() {
        return userList;
    }

}
