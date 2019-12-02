package com.joantolos.kata.scores.domain.entity;

public class Score {

    private String username;
    private Integer score;

    public Score(String username, Integer score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public Integer getScore() {
        return score;
    }
}