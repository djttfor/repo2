package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Goods;
import com.qingcheng.pojo.goods.Spu;

import java.util.List;
import java.util.Map;

/**
 * spu业务逻辑层
 */
public interface SpuService {


    public List<Spu> findAll();


    public PageResult<Spu> findPage(int page, int size);


    public List<Spu> findList(Map<String, Object> searchMap);


    public PageResult<Spu> findPage(Map<String, Object> searchMap, int page, int size);


    public Spu findById(String id);

    public void add(Spu spu);


    public void update(Spu spu);


    public void delete(String id);

    public void saveGoods(Goods goods);

    Goods findGoodsById(String id);

    void audit(String id,String status,String message);

    void pull(String id);

    void put(String id);

    int pullMany(String[] ids);

    int putMany(String[] ids);

    int logicDeleteById(String id);

    int reductionGoods(String id);

}
