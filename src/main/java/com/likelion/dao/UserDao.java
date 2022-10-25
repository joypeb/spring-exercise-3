package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        Factory factory = new Factory();
        this.connectionMaker = factory.connectionMaker();
    }


    public void add(final User user) throws ClassNotFoundException, SQLException {

        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into likelionDB.users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User findById(String id) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = connectionMaker.makeConnection();
            ps = c.prepareStatement("select * from likelionDB.users where id = ?");
            ps.setString(1,id);

            rs = ps.executeQuery();

            User user = null;

            if(rs.next()) {
                user = new User(rs.getString(1),rs.getString(2),rs.getString(3));
            }

            if (user == null) throw new EmptyResultDataAccessException(1);

            rs.close();
            ps.close();
            c.close();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
