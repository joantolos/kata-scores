package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.dao.H2Scores;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.LoginOutput;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class ScoresService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private H2Scores h2Scores;

    public LoginOutput getLoginOutput(LoginInput loginInput) throws AuthenticationException, SQLException {
        return new LoginOutput(this.authenticationService.newToken(loginInput));
    }

    public void addLevel(int level, int score, String token) throws AuthenticationException, SQLException {
        if (this.authenticationService.isTokenValid(token)) {
            this.h2Scores.insertScore(level, score, this.authenticationService.getUsername(token));
        } else {
            throw new AuthenticationException("Invalid token " + token);
        }
    }
}
