package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.IntegerSyntax;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    BrandService brandService;
    @RequestMapping("/findAll")//查询所有品牌
    public List<Brand> findAll(){
        return brandService.findAll();
    }
    @RequestMapping("/findPage") /*只能用get方式请求用此注解*/ //分页查询所有品牌
    public PageResult<Brand> findPage(int page,int size){
        return brandService.findPage(page, size);
    }
    @PostMapping("/findList")//按条件查询品牌
    public List<Brand> findList(@RequestBody Map<String,Object> searchMap){
        return brandService.findList(searchMap);
    }
    @GetMapping("/findPage")//分页按条件查询品牌
    public PageResult<Brand> findPage(int page,int size,String name,String letter){
        return brandService.findPage( page, size,name,letter);
    }
    @PostMapping("/findPage")
    public PageResult<Brand> findPage(Map<String ,Object > searchMap,int page,int size){
        return brandService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById")
    public Brand findById(Integer id){
        return brandService.findById(id);
    }
    @PostMapping("/addBrand")
    public Result AddBrand(@RequestBody Brand brand){
        brandService.AddBrand(brand);
        return new Result();
    }
    @PostMapping("/updateBrand")
    public Result updateBrand(@RequestBody Brand brand){
        brandService.updateBrand(brand);
        return new Result();
    }
    @GetMapping("/deleteBrand")
    public Result deleteBrand(Integer id){
        brandService.deleteBrand(id);
        return new Result();
    }
}
