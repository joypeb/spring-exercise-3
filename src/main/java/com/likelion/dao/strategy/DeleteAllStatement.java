package com.likelion.dao.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy{
    @Override
    public PreparedStatement makePreparedStatement(Connection c) {
        try {
            PreparedStatement ps = c.prepareStatement("delete from likelionDB.users");

            return ps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
