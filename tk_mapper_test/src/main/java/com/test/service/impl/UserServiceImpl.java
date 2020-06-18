package com.test.service.impl;

import com.test.mapper.UserMapper;
import com.test.pojo.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    public void updateUser(User user) {
        System.out.println(userMapper.updateByPrimaryKeySelective(user));

    }

    public void deleteUserById(Integer id) {
        System.out.println(userMapper.deleteByPrimaryKey(id));
    }

    public void saveUser(User user) {
        System.out.println(userMapper.insertSelective(user));
    }

    public List<User> findByExample() {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("id>1 and id < 3");
        return userMapper.selectByExample(example);
    }

    public void updateByExample(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("id>1");
        System.out.println(userMapper.updateByExampleSelective(user, example));
    }

}
