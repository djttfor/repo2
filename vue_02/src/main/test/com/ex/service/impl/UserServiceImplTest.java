package com.ex.service.impl;

import com.ex.domain.User;
import com.ex.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void findAllUser() {
        List<User> allUser = userService.findAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId("1");
        user.setUsername("hehe");
        user.setPassword("23456");
        System.out.println(userService.updateUser(user));
    }
    @Test
    public void findUserById(){
        User user = userService.findUserById(1);
        System.out.println(user);
    }
}