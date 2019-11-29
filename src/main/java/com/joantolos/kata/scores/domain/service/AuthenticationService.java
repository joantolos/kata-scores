package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.dao.InMemoryAuthentication;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthenticationService {

    @Value("${token.timeout}")
    private String tokenTimeout;

    @Autowired
    private InMemoryAuthentication inMemoryAuthentication;
    private Map<String, Timestamp> tokens;

    @PostConstruct
    public void init() {
        this.tokens = new HashMap<>();
    }

    public String newToken(LoginInput loginInput) throws AuthenticationException {
        if (this.inMemoryAuthentication.isValid(loginInput)) {
            String token = UUID.randomUUID().toString();
            this.addToken(token);
            return token;
        } else {
            throw new AuthenticationException("Invalid login");
        }
    }

    private synchronized void addToken(String token) {
        this.tokens.put(token, new Timestamp(System.currentTimeMillis()));
    }

}
