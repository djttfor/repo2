package my.dao;

import my.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class AccountDaoTest {
    @Autowired
    AccountDao accountDao;
    @Test
    public void findAll(){
        List<Account> accountDaoAll = accountDao.findAll();
        for (Account account : accountDaoAll) {
            System.out.println(account);
        }
    }

    //测试git修改提交
    @Test
    public void save(){
        Account account = new Account();
        account.setUid(69);
        account.setMoney(1000.2);
        account.setAcname("呵呵");
        account.setPassword("2233");
        int save = accountDao.save(account);
        System.out.println(save);
    }
}