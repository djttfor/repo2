package com.ex.ssm.controller;

import com.ex.ssm.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-02
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/test")
    @ResponseBody
    public Map<String,Object> test1(User user){
        Map<String,Object> map = new HashMap<>();
        if("jimmy".equals(user.getUsername())&&"123".equals(user.getPassword())){
            map.put("message","登录成功");
        }else{
            map.put("message","登录失败");
        }
        return map;
    }
}