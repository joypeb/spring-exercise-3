package com.likelion.dao;

import com.likelion.dao.strategy.StatementStrategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt){
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = dataSource.getConnection();
            pstmt = stmt.makePreparedStatement(c);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    new Exception(e);
                }
            }

            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    new Exception(e);
                }
            }
        }
    }
}
