package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Factory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao;

    User u1;
    User u2;

    @BeforeEach
    public void before() {
        userDao = context.getBean("userDao", UserDao.class);
        this.u1 = new User("11", "test1", "pw2");
        this.u2 = new User("12", "test2", "pw22");
    }

    @Test
    public void test1() {
        userDao.deleteAll();
        userDao.add(this.u1);

        User user = userDao.findById(this.u1.getId());

        assertEquals(this.u1.getName(), user.getName());

    }

    @Test
    public void getTest() {
        userDao.deleteAll();

        List<User> users = userDao.getAll();

        //빈 리스트 리턴
        assertEquals(0,users.size());

        userDao.add(u1);
        userDao.add(u2);

        users = userDao.getAll();

        assertEquals(2, users.size());
    }
}