package my.service.impl;

import com.github.pagehelper.PageHelper;
import my.dao.ProductDao;
import my.domain.Product;
import my.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public List<Product> findAll() {
        //PageHelper.startPage();
        return productDao.findAll();
    }

    @Override
    /**
    * Description:
    * <分页查询产品>
    * @param pageNum
    * @param pageSize
    * @return: java.util.List<my.domain.Product>
    * @Author: DJ
    * @Date: 2020-03-29 16:40
    */
    public List<Product> findAllPaging(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return productDao.findAll();
    }

    @Override
    public int saveProduct(Product product) {
        return productDao.saveProduct(product);
    }

    @Override
    public Product findById(String id) throws Exception {
        return productDao.findProductById(id);
    }
}
