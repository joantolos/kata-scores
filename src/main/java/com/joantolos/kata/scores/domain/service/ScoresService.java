package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.LoginOutput;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoresService {

    @Autowired
    private AuthenticationService authenticationService;

    public LoginOutput getLoginOutput(LoginInput loginInput) throws AuthenticationException {
        return new LoginOutput(this.authenticationService.newToken(loginInput));
    }

    public void addLevel(int level, int score) throws AuthenticationException {

    }
}
