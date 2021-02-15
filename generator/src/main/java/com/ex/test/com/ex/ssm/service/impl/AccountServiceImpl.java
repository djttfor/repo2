package com.ex.test.com.ex.ssm.service.impl;

import com.ex.test.com.ex.ssm.entity.Account;
import com.ex.test.com.ex.ssm.mapper.AccountMapper;
import com.ex.test.com.ex.ssm.service.AccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}