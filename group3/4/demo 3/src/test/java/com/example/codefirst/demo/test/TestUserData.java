package com.example.codefirst.demo.test;

import com.example.codefirst.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class TestUserData {

    public static List<User> getStabUserList() {
        List<User> users = new ArrayList<>();
        users.add(createUser("test1", "test1", "test1", "qwe"));
        users.add(createUser("test2", null, "", "asd"));


        return users;
    }

    private static User createUser(String firstName, String lastName, String middleName, String password) {
        return new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMiddleName(middleName)
                .setPassword(password);
    }
}
