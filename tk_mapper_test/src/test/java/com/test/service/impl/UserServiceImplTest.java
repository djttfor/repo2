package com.test.service.impl;

import com.test.pojo.User;
import com.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Test
    public void findAll() {
        List<User> all = userService.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        user.setIsEnable("1");
        user.setUsername("hehe");
        user.setPassword("123");
        userService.updateUser(user);
    }
    @Test
    public void save() {
        User user = new User();
        user.setUsername("hehe2");
        userService.saveUser(user);
    }
    @Test
    public void delete() {
        userService.deleteUserById(5);
    }
    @Test
    public void selectByExample() {
        List<User> byExample = userService.findByExample();
        for (User user : byExample) {
            System.out.println(user);
        }
    }
    @Test
    public void updateByExample() {
        User user = new User();
        user.setIsEnable("0");
        user.setUsername("hehe");
        userService.updateByExample(user);
    }

}