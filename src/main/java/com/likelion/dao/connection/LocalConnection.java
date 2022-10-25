package com.likelion.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalConnection implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306",
                "root",
                "1234"
        );
        return c;
    }
}
