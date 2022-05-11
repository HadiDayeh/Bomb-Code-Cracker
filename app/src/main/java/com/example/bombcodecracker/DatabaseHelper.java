package com.example.bombcodecracker;

import java.sql.*;

public class DatabaseHelper {
    private static Connection CONNECTION;
    public static Statement STATEMENT;

    public static void openConnection() throws SQLException, ClassNotFoundException {
        String host = "localhost";
        String port = "3306";
        String database = "unlock_the_treasure";
        String charset = "?characterEncoding=latin1&useConfigs=maxPerformance";
        String user = "root";
        String password = "";

        Class.forName("com.mysql.jdbc.Driver");
        CONNECTION = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + charset, user, password);
        STATEMENT = CONNECTION.createStatement();
    }

    public static void closeConnection() throws SQLException {
        CONNECTION.close();
    }
}