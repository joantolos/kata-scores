package com.joantolos.kata.scores.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StringUtilsTest {

    @Test
    public void shouldGetStringFromInputStream() throws IOException {
        String createDatabase = StringUtils.toString(this.getClass().getResourceAsStream("/createDatabase.sql"));

        Assert.assertTrue(createDatabase.contains("DROP TABLE IF EXI"));
    }

}