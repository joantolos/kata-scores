package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.LoginInput;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InMemoryScores implements ScoresDAO {

    @Override
    public boolean isValid(LoginInput loginInput) {
        return Login.isValid(loginInput);
    }

    private enum Login {

        LUKE_SKYWALKER("luke", "123"),
        DARTH_VADER("vader", "456"),
        YODA("yoda", "789");

        String user;
        String password;

        Login(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public static boolean isValid(LoginInput loginInput) {
            return Arrays.stream(Login.values()).anyMatch(login -> login.user.equals(loginInput.getUser()) && login.password.equals(loginInput.getPassword()));
        }
    }
}
