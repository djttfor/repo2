package com.ex.dao;

import com.ex.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    UserDao userDao;
    @Test
    public void findAllUser() {
        List<User> allUser = userDao.findAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUser() {
        User user =new User();
        user.setId("1");
        user.setUsername("今晚打老虎");
        user.setPassword("1223");
        userDao.updateUser(user);
    }
    @Test
    public void test1(){

    }
}