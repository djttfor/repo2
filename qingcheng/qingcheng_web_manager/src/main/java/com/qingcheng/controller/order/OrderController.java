package com.qingcheng.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.order.Hehe;
import com.qingcheng.pojo.order.Order;
import com.qingcheng.pojo.order.OrderFull;
import com.qingcheng.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @GetMapping("/findAll")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Order> findPage(int page, int size){
        return orderService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Order> findList(@RequestBody Map<String,Object> searchMap){
        return orderService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Order> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  orderService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Order findById(String id){
        return orderService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Order order){
        orderService.add(order);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Order order){
        orderService.update(order);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        orderService.delete(id);
        return new Result();
    }

    @GetMapping("/findFullOrderById")
    public OrderFull findFullOrderById(String id){
        return orderService.findFullOrderById(id);
    }

    @PostMapping("/ships")
    public Result orderShips(@RequestBody List<Order> orders){
        orderService.orderShips(orders);
        return new Result();
    }
    @PostMapping("/returnApplication")
    public Result returnApplication(@RequestBody Map<String ,String> map){
        orderService.returnApplication(map);
        return new Result();
    }
    @PostMapping("/ou")
    public Result updateOrder(@RequestBody Map<String,String> map){
        orderService.updateOrder(map);
        return new Result();
    }
    @PostMapping("/hehe")
    public Result updateOrder(@RequestBody Hehe hehe){
        orderService.updateOrder(hehe);
        return new Result();
    }
}
