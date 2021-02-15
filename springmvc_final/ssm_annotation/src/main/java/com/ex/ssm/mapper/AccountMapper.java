package com.ex.ssm.mapper;

import com.ex.ssm.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-15
 */
public interface AccountMapper extends BaseMapper<Account> {
    Account queryAccountByUid(Integer uid);
}