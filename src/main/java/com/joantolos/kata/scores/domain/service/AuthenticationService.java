package com.joantolos.kata.scores.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;

@Component
public class AuthenticationService {

    @Value("${token.timeout}")
    private String tokenTimeout;

    private Map<String, Timestamp> tokens;

    public String newToken() {
        return "123";
    }
}
