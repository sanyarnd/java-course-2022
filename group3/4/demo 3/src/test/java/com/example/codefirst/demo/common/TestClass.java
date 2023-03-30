package com.example.codefirst.demo.common;

public class TestClass {

    public String getFirst() {
        return "First!";
    }

    public String getSecond() {
        return "Second!";
    }

    public String getThird() {
        return "Third!";
    }

    public String getFourth() {
        throw new NullPointerException();
    }

}
