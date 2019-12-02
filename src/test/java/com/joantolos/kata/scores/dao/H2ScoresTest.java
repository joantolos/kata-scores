package com.joantolos.kata.scores.dao;

import com.joantolos.kata.scores.domain.dao.H2Scores;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class H2ScoresTest {

    @Autowired
    private H2Scores h2Scores;

    @Test
    public void shouldValidateLogin() throws SQLException {
        Assert.assertTrue(h2Scores.isValid(new LoginInput("luke", "123")));
    }
}
