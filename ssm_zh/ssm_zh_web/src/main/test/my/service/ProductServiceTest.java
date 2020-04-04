package my.service;

import my.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void findAll(){
        List<Product> all = productService.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    @Test
    public void saveProduct(){
        Product product = new Product();
        product.setCityName("北京");
        product.setDepartureTime(new Date());
        product.setProductName("伊拉克十二日游");
        product.setProductNumber("ylk023");
        product.setProductPrice(8888.8);
        product.setProductDesc("非常好玩啊哈哈哈");
        product.setProductStatus(1);
        int i = productService.saveProduct(product);
        System.out.println(i);
    }

    @Test
    public void findAllPaging(){
        List<Product> list = productService.findAllPaging(1, 4);
        for (Product product : list) {
            System.out.println(product);
        }
    }

}