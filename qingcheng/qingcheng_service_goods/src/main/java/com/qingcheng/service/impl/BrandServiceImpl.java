package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override //查询所有品牌
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override//分页查询品牌
    public PageResult<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> pageResult =(Page<Brand>) brandMapper.selectAll();
        return new PageResult<Brand>(pageResult.getTotal(),pageResult.getResult());
    }

    @Override //品牌的条件查询包括模糊查询
    public List<Brand> findList(Map<String, Object> searchMap) {
        return brandMapper.selectByExample(createExample(searchMap));
    }

    @Override//分页按条件查询品牌
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(createExample(searchMap));
        return new PageResult<Brand>(brands.getTotal(),brands.getResult());
    }

    @Override
    public PageResult<Brand> findPage(int page, int size, String name, String letter) {
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("name",name);
        map.put("letter",letter);
        PageHelper.startPage(page,size);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(createExample(map));
        return new PageResult<Brand>(brands.getTotal(),brands.getResult());
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void AddBrand(Brand brand) {
        brandMapper.insert(brand);
    }
    @Override
    public void deleteBrand(Integer id){
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }


    private Example createExample(Map<String,Object> searchMap){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if(searchMap!=null){
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name") )) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            if(searchMap.get("letter")!= null && !"".equals(searchMap.get("letter"))){
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }
        return example;
    }

}
