package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.Filters;
import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.Score;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        String getQuery;

        switch (filter) {
            case HIGH_SCORE:
                getQuery = "SELECT s.username, MAX(s.score) as score FROM score s WHERE s.level = " + level + " GROUP BY s.username";
                break;
            default:
                getQuery = "SELECT s.username, s.score as score FROM score s WHERE s.level = " + level;
        }

        return parseScores(super.executeQuery(getQuery, Arrays.asList("username", "score")));
    }

    private List<Score> parseScores(List<Object> scores) {

        return IntStream.range(0, scores.size())
                .mapToObj(index -> {
                    if(scores.get(index) instanceof String) {
                        return String.format("%d -> %s", index, scores.get(index));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(score -> new Score(score.split(" -> ")[1], (Integer) scores.get(Integer.parseInt(score.split(" -> ")[0]) + 1)))
                .collect(Collectors.toList());
    }

}