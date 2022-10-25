package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Factory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao;

    User u1;

    @BeforeEach
    public void before() {
        userDao = context.getBean("userDao", UserDao.class);
        this.u1 = new User("11","test1","pw2");
    }

    @Test
    public void test1() {
        try {
            userDao.add(this.u1);
            User user = userDao.findById(this.u1.getId());

            assertEquals(this.u1.getName(),user.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}