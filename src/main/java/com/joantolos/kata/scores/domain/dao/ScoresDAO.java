package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.Filters;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.Score;

import java.sql.SQLException;
import java.util.List;

public interface ScoresDAO {

    boolean isValid(LoginInput loginInput) throws SQLException;

    void insertScore(int level, int score, String username) throws SQLException;

    List<Score> retrieveScore(int level, Filters filter) throws SQLException;
}
