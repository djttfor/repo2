package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    List<Brand> findAll();

    PageResult<Brand> findPage(int page,int size);

    List<Brand> findList(Map<String,Object> searchMap);

    PageResult<Brand> findPage(Map<String,Object> searchMap,int page,int size);

    PageResult<Brand> findPage(int page,int size,String name,String letter);

    Brand findById(Integer id);

    void AddBrand(Brand brand);

    void deleteBrand(Integer id);

    void updateBrand(Brand brand);
}
