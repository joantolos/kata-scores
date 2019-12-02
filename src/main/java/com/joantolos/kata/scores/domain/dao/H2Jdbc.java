package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.controller.ScoresController;
import com.joantolos.kata.scores.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class H2Jdbc {

    private final Logger log = LoggerFactory.getLogger(ScoresController.class);

    @Autowired
    private StringUtils stringUtils;

    @Value("${jdbc.url}")
    private String jdbcURL;

    @Value("${jdbc.user}")
    private String jdbcUser;

    @Value("${database.script}")
    private String databaseScript;

    private Connection connection = null;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection(jdbcURL, jdbcUser, "");

        String createDatabase = stringUtils.toString(this.getClass().getResourceAsStream("/" + databaseScript));
        this.executeStatement(createDatabase);
    }

    public void executeStatement(String statementSQL) throws SQLException {
        log.info(statementSQL);
        try (Statement statement = connection.createStatement()) {
            statement.execute(statementSQL);
        }
    }

    public List<Object> executeQuery(String querySQL, List<String> columnNames) throws SQLException {
        List<Object> queryResults = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(querySQL);

            while (rs.next()) {
                for (String columnName : columnNames) {
                    queryResults.add(rs.getObject(columnName));
                }
            }
        }

        return queryResults;
    }
}