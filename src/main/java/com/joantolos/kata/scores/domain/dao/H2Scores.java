package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.Filters;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.Score;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class H2Scores extends H2Jdbc implements ScoresDAO {

    @Override
    public boolean isValid(LoginInput loginInput) throws SQLException {
        String loginQuery = "SELECT username, password FROM users u WHERE u.username = '" + loginInput.getUser() + "' AND u.password = '" + loginInput.getPassword() + "'";
        return super.executeQuery(loginQuery, Arrays.asList("username", "password")).size() > 0;
    }

    @Override
    public void insertScore(int level, int score, String username) throws SQLException {
        String insertQuery = "INSERT INTO score (username, level, score) VALUES ('" + username +  "', " + level + ", " + score + ")";
        super.executeStatement(insertQuery);
    }

    @Override
    public List<Score> retrieveScore(int level, Filters filter) throws SQLException {
        String getQuery = "";

        switch (filter) {
            case HIGH_SCORE:
                getQuery = "SELECT s.username, MAX(s.score) as score FROM score s WHERE s.level = " + level + " GROUP BY s.username";
                break;
            default:
                getQuery = "SELECT s.username, s.score as score FROM score s WHERE s.level = " + level;
        }

        List<Object> scores = super.executeQuery(getQuery, Arrays.asList("username", "score"));
        Map<String, Integer> parsedScores = new HashMap<>();
        for (int i = 0; i < scores.size(); i++) {
            if(scores.get(i) instanceof String) {
                parsedScores.put(String.valueOf(scores.get(i)), (Integer) scores.get(i + 1));
            }
        }

        return parsedScores.entrySet().stream().map(parsedScore -> new Score(parsedScore.getKey(), parsedScore.getValue())).collect(Collectors.toList());
    }
}