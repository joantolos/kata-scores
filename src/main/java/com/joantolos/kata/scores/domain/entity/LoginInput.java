package com.joantolos.kata.scores.domain.entity;

public class LoginInput {

    private String user;
    private String password;

    public LoginInput(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
