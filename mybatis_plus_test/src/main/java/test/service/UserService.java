package test.service;

import test.pojo.User;

import java.util.List;
public interface UserService {
    List<User> findAll();
    void save(User user);
    void delete(String id);
    void updateById(User user);
}
