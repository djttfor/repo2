package test.service.impl;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.pojo.User;
import test.service.UserService;

import java.util.List;

import static org.junit.Assert.*;
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
    public void save() {
        User user = new User();
        user.setId("1");
        user.setUsername("hehe2");
        userService.save(user);
    }

    @Test
    public void delete() {

    }

    @Test
    public void updateById() {
    }
}