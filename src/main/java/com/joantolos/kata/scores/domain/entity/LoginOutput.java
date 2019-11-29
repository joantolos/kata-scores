package com.joantolos.kata.scores.domain.entity;

public class LoginOutput {

    private String token;

    public LoginOutput(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
