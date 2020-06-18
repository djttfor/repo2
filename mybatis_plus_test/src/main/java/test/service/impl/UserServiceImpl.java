package test.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.mapper.UserMapper;
import test.pojo.User;
import test.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    public void save(User user) {

        try {
            System.out.println(userMapper.insert(user));
        } catch (Exception e) {
            logger.error("错误信息:",e);
        }
    }

    public void delete(String id) {
        System.out.println(userMapper.deleteById(id));
    }

    public void updateById(User user) {
        userMapper.updateById(user);
    }
}
