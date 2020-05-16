package com.ex.service;

import com.ex.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User findUserById(Integer id);
    Integer updateUser(User user);
}
