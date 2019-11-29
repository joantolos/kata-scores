package com.joantolos.kata.scores.domain.service;

import com.joantolos.kata.scores.domain.entity.LoginInput;
import org.apache.tomcat.websocket.AuthenticationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @Before
    public void setUp() {
        this.authenticationService = new AuthenticationService();
    }

    @Test
    public void shouldCreateNewToken() throws AuthenticationException {
        Assert.assertNotNull(authenticationService.newToken(new LoginInput("joan","password")));
    }
}
