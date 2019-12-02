package com.joantolos.kata.scores.domain.entity;

import java.util.Arrays;

public enum Filters {

    HIGH_SCORE("highestscore"),
    NONE("");

    private String name;

    Filters(String name) {
        this.name = name;
    }

    public static Filters getByName(String name) {
        return Arrays.stream(Filters.values()).filter(filter -> filter.name.equals(name)).findFirst().orElse(Filters.NONE);
    }
}
