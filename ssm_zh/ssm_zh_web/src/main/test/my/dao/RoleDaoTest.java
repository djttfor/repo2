package my.dao;

import my.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class RoleDaoTest {
    @Autowired
    RoleDao roleDao;
    @Test
    public void findRoleByUserId() throws Exception {
        List<Role> role = roleDao.findRoleByUserId("036E71474B011C3ACE6D859893B49168");
        for (Role role1 : role) {
            System.out.println(role1);
        }
    }
}
