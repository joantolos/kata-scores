package com.joantolos.kata.scores.domain.entity;

import java.sql.Timestamp;

public class TokenContent {

    private Timestamp loginTime;
    private String username;

    public TokenContent(Timestamp loginTime, String username) {
        this.loginTime = loginTime;
        this.username = username;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public String getUsername() {
        return username;
    }
}
