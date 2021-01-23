import com.ex.smp.config.ISpringConfig;
import com.ex.smp.service.IAccountService;
import com.ex.smp.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ISpringConfig.class})
public class MybatisPlusSpringJavaConfigTest {
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
    @Test
    public void queryWrapper(){
        accountService.transfer(1,2,1000L);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext an =
                new AnnotationConfigApplicationContext(ISpringConfig.class);
        an.getBean("user");
    }
}
