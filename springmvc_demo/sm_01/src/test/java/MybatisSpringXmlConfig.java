import com.ex.mapper.UserMapper;
import com.ex.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring-context.xml")
public class MybatisSpringXmlConfig {
    @Autowired
    UserMapper userMapper;
    @Test
    public void findAll(){
        User jimmy = userMapper.findUserByCondition(3, "jimmy");
        System.out.println("第一次查询:"+jimmy);
        User jimmy2 = userMapper.findUserByCondition(3, "jimmy");
        System.out.println("第二次查询:"+jimmy2);
        //sqlSession.commit();
        User jimmy3 = userMapper.findUserByCondition(3, "jimmy");
        System.out.println("第三次查询:"+jimmy3);
    }
}
