package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.LoginInput;

import java.sql.SQLException;

public interface ScoresDAO {

    boolean isValid(LoginInput loginInput) throws SQLException;
}
