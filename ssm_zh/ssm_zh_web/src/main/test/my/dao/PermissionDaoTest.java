package my.dao;

import my.domain.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class PermissionDaoTest {
    @Autowired
    PermissionDao permissionDao;
    @Test
    public void findByRoleId() throws Exception {
        List<Permission> permissions = permissionDao.findByRoleId("1538AAC2DD996145FC4B7162CBF266EC");
        for (Permission permission : permissions) {
            System.out.println(permission);
        }
    }
}
