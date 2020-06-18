package com.test.service;

import com.test.mapper.UserMapper;
import com.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public interface UserService {
    List<User> findAll();
    void updateUser(User user);
    void deleteUserById(Integer id);
    void saveUser(User user);
    List<User> findByExample();
    void updateByExample(User user);
}
