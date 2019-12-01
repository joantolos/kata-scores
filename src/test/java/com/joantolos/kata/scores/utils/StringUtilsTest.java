package com.joantolos.kata.scores.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void shouldGetStringFromInputStream() {
        String createDatabase = StringUtils.toString(this.getClass().getResourceAsStream("/createDatabase.sql"));

        Assert.assertTrue(createDatabase.contains("DROP TABLE IF EXI"));
    }

}
