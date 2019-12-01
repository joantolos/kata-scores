package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.LoginInput;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;

@Component
public class H2Scores extends H2Jdbc implements ScoresDAO {

    @Override
    public boolean isValid(LoginInput loginInput) throws SQLException {
        String loginQuery = "SELECT username, password FROM user u WHERE u.username = '" + loginInput.getUser() + "' AND u.password = '" + loginInput.getPassword() + "'";
        return super.executeQuery(loginQuery, Arrays.asList("username", "password")).size() > 0;
    }

}
