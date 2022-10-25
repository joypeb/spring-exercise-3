package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {


    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void add(final User user) {
        this.jdbcTemplate.update("INSERT INTO likelionDB.users(id,name,password) values (?,?,?);",
                user.getId(),user.getName(),user.getPassword());

    }

    public void deleteAll() {
       this.jdbcTemplate.update("delete from likelionDB.users;");
    }

    public int getCount() {
        int cnt = this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM likelionDB.users;",Integer.class);
        return cnt;
    }

    public User findById(String id) {
        String sql = "SELECT * FROM likelionDB.users WHERE id = ?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM likelionDB.users ORDER BY id;";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"),rs.getString("name"),rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
