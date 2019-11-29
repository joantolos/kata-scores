package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.entity.LoginInput;

import java.util.Arrays;

public enum Login {

    LUKE_SKYWALKER("luke", "123"),
    DARTH_VADER("vader", "456"),
    YODA("yoda", "789");

    private String user;
    private String password;

    Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static boolean isValid(LoginInput loginInput) {
        return Arrays.stream(Login.values()).anyMatch(login -> login.user.equals(loginInput.getUser()) && login.password.equals(loginInput.getPassword()));
    }
}
