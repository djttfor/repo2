package com.ex.test.com.ex.ssm.service.impl;

import com.ex.test.com.ex.ssm.entity.User;
import com.ex.test.com.ex.ssm.mapper.UserMapper;
import com.ex.test.com.ex.ssm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}