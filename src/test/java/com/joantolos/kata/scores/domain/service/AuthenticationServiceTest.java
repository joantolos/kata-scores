package com.joantolos.kata.scores.domain.service;

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
    public void shouldCreateNewToken() {
        Assert.assertNotNull(authenticationService.newToken());
    }
}
