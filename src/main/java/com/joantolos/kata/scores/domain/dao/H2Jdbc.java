package com.joantolos.kata.scores.domain.dao;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class H2Jdbc {

    private static String jdbcURL = "jdbc:h2:~/test";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

    @PostConstruct
    public void init() throws SQLException {
        this.createTable();
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
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

    public void createTable() throws SQLException {

        String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
                "  name varchar(20),\r\n" + "  email varchar(20),\r\n" + "  country varchar(20),\r\n" +
                "  password varchar(20)\r\n" + "  );";

        System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = this.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            this.printSQLException(e);
        }
    }
}
