package com.likelion.dao.strategy;

import com.likelion.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy{

    User user;

    public AddStatement(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection c) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO likelionDB.users(id, name, password) VALUES(?,?,?)");

            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            return ps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
