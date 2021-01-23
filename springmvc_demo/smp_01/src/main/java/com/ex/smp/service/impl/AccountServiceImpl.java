package com.ex.smp.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.smp.entity.Account;
import com.ex.smp.mapper.AccountMapper;
import com.ex.smp.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ttfor
 * @since 2021-01-18
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountMapper accountMapper;

    @Override
    @Transactional
    public Boolean transfer(Integer rid, Integer tid, Long balance) {
        Long resourceBalance = getBalance(rid);
        if(resourceBalance<balance){
            throw new RuntimeException("账户"+rid+"余额不足");
        }
        Long targetBalance = getBalance(tid);
        Boolean result ;
         updateBalance(rid,resourceBalance-balance);
         int a = 1/0;
        result = updateBalance(tid, targetBalance + balance);
        return result;
    }
    public Boolean updateBalance(Integer aid,Long balance){
        Account account = new Account();
        UpdateWrapper<Account> update = Wrappers.update(account);
        update.set("balance",balance);
        update.eq("id",aid);
        return accountMapper.update(account,update)>0;
    }

    public Long getBalance(Integer accountId){
        Account rAccount = accountMapper.selectOne(Wrappers.lambdaQuery(new Account()).eq(Account::getId, accountId));
        if(rAccount==null){
            throw new RuntimeException("账户"+accountId+"不存在");
        }else{
            return rAccount.getBalance();
        }
    }
}
