package com.ex.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ex.ssm.entity.User;
import com.ex.ssm.mapper.UserMapper;
import com.ex.ssm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public LambdaQueryWrapper<User> getQueryWrapper(){
        return Wrappers.lambdaQuery(User.class);
    }
    @Override
    public List<User> queryAllByNestedResult() {
        return userMapper.queryAllByNestedResult();
    }
}