package my.dao;

import my.domain.UserInfo;
import my.util.PasswordEncoderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void findByUsername() throws Exception {
        UserInfo userInfo = userDao.findByUsername("jack");
        System.out.println(userInfo);
    }
    @Test
    public void findAll() throws Exception {
        List<UserInfo> all = userDao.findAll();
        for (UserInfo userInfo : all) {
            System.out.println(userInfo);
        }
    }
    @Test
    public void saveUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("12345@qq.com");
        userInfo.setUsername("Jk");
        userInfo.setPassword(PasswordEncoderUtil.encodePassword("123"));
        userInfo.setPhoneNum("10087");
        userInfo.setStatus(1);
        int i = userDao.saveUser(userInfo);
        System.out.println(i);
    }

    @Test
    public void checkUsernameExist(){
        System.out.println(userDao.checkUsernameExist("jimmy"));
    }
}
