package my.dao;


import my.domain.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class OrdersDaoTest {
    @Autowired
    OrdersDao ordersDao;
    @Test
    public void findAll() throws Exception {
        List<Orders> all = ordersDao.findAll();
        for (Orders orders : all) {
            System.out.println(orders);
        }
    }
    @Test
    public void findOrdersById() throws Exception {
        Orders orsers = ordersDao.findOrdersById("1A0BF2146D9A11EA9194000C29C426BD" +
                "");
        System.out.println(orsers);
    }
}
