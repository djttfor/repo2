package com.ex.controller;

import com.ex.mapper.UserMapper;
import com.ex.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);
    @Autowired
    UserMapper userMapper;
    @GetMapping("/say")
    public List<User> sayHello(){
        logger.error("log4j2,尼玛死了");
        return userMapper.findAll();
    }
}
