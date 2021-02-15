import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ex.ssm.config.ISpringConfig;
import com.ex.ssm.entity.Member;
import com.ex.ssm.entity.User;
import com.ex.ssm.mapper.AccountMapper;
import com.ex.ssm.mapper.MemberMapper;
import com.ex.ssm.mapper.UserMapper;
import com.ex.ssm.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.management.ManagementFactory;
import java.util.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ISpringConfig.class})
public class Test1 {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    MemberMapper memberMapper;

    @Test
    public void query(){
        List<User> users = userService.queryAllByNestedResult();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void query2(){
        List<User> users = userMapper.queryAllByNestedQuery();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void query3(){
        List<User> users = userMapper.aQueryAllByNestedResult();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void query4(){
        List<User> users = userMapper.aQueryAllByNestedQuery();
        for (User user : users) {
            System.out.println(user);
        }
    }
    volatile static int a = 0;
    public static void main(String[] args) throws InterruptedException {

    }
}
