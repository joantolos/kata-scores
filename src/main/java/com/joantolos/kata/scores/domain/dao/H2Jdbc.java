package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.controller.ScoresController;
import com.joantolos.kata.scores.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class H2Jdbc {

    private final Logger log = LoggerFactory.getLogger(ScoresController.class);

    @Value("${jdbc.url}")
    private String jdbcURL;

    @Value("${jdbc.user}")
    private String jdbcUser;

    private Connection connection = null;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection(jdbcURL, jdbcUser, "");

        String createDatabase = StringUtils.toString(this.getClass().getResourceAsStream("/createDatabase.sql"));
        this.executeStatement(createDatabase);
    }

    public void executeStatement(String statementSQL) throws SQLException {
        log.info(statementSQL);
        try (Statement statement = connection.createStatement()) {
            statement.execute(statementSQL);
        }
    }
}
