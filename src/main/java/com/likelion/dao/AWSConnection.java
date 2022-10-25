package com.likelion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AWSConnection implements ConnectionMaker{
    public Connection makeConnection() throws ClassNotFoundException,SQLException {
        Map<String, String> env = System.getenv();

        Connection c = DriverManager.getConnection(env.get("DB_HOST"),env.get("DB_USER"),env.get("DB_PASSWORD"));

        return c;
    }
}