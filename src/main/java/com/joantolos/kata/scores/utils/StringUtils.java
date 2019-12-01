package com.joantolos.kata.scores.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtils {

    public static String toString(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
