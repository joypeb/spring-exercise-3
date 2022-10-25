package com.likelion.dao;

import com.likelion.dao.strategy.StatementStrategy;
import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }


    public void add(final User user) {
        StatementStrategy stmt = new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) {
                PreparedStatement ps = null;
                try {
                    ps = c.prepareStatement("INSERT INTO likelionDB.users(id, name, password) VALUES(?,?,?)");
                    ps.setString(1, user.getId());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());
                    return ps;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        jdbcContext.workWithStatementStrategy(stmt);
    }

    public void deleteAll() {
        StatementStrategy stmt = new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) {
                try {
                    return c.prepareStatement("DELETE FROM likelionDB.users");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        jdbcContext.workWithStatementStrategy(stmt);
    }

    public User findById(String id) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            ps = c.prepareStatement("select * from likelionDB.users where id = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();

            User user = null;

            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
            }

            if (user == null) throw new EmptyResultDataAccessException(1);

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }


    }
}
