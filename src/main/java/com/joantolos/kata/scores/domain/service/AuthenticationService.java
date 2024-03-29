package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.dao.H2Scores;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.TokenContent;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthenticationService {

    @Value("${token.timeout}")
    private Long tokenTimeout;

    @Autowired
    private H2Scores h2Scores;

    private Map<String, TokenContent> tokens;

    @PostConstruct
    public void init() {
        this.tokens = new HashMap<>();
    }

    public String newToken(LoginInput loginInput) throws AuthenticationException, SQLException {
        if (this.h2Scores.isValid(loginInput)) {
            String token = UUID.randomUUID().toString();
            this.addToken(token, loginInput.getUser());
            return token;
        } else {
            throw new AuthenticationException("Invalid login");
        }
    }

    boolean isTokenValid(String token) {
        return tokens.containsKey(token) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        boolean isExpired = (System.currentTimeMillis() - tokens.get(token).getLoginTime().getTime()) > tokenTimeout;
        if(isExpired) {
            removeToken(token);
        }
        return isExpired;
    }

    private synchronized void addToken(String token, String username) {
        this.tokens.put(token, new TokenContent(new Timestamp(System.currentTimeMillis()), username));
    }

    private synchronized void removeToken(String token) {
        this.tokens.remove(token);
    }

    public String getUsername(String token) {
        return this.tokens.get(token).getUsername();
    }
}
