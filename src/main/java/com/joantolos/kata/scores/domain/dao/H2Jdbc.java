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

    @PostConstruct
    public void init() throws SQLException {
        String createDatabase = StringUtils.toString(this.getClass().getResourceAsStream("/createDatabase.sql"));
        this.executeStatement(createDatabase);
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUser, "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void executeStatement(String statementSQL) throws SQLException {

        log.info(statementSQL);

        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(statementSQL);

        } catch (SQLException e) {
            this.printSQLException(e);
        }
    }
}
