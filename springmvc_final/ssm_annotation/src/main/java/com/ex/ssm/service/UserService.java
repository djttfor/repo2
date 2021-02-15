package com.ex.ssm.service;

import com.ex.ssm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-15
 */
public interface UserService extends IService<User> {
    List<User> queryAllByNestedResult();
}