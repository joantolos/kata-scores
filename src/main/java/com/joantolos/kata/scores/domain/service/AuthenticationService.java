package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.dao.InMemoryAuthentication;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;

@Component
public class AuthenticationService {

    @Value("${token.timeout}")
    private String tokenTimeout;

    @Autowired
    private InMemoryAuthentication inMemoryAuthentication;

    private Map<String, Timestamp> tokens;

    public String newToken(LoginInput loginInput) throws AuthenticationException {
        if (this.inMemoryAuthentication.isValid(loginInput)) {
            return "123";
        } else {
            throw new AuthenticationException("Invalid login");
        }
    }

}
