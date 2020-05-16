package com.ex.controller;

import com.ex.domain.User;
import com.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
//    @RequestMapping("/all")
//    public ModelAndView findAllUser(){
//        List<User> allUser = userService.findAllUser();
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("users",allUser);
//        mv.setViewName("");
//        return mv;
//    }
    @RequestMapping("/all")
    public @ResponseBody List<User> findAllUser(){
        return userService.findAllUser();
    }
    @RequestMapping("/update")
    public @ResponseBody Integer updateUser(User user){
        return userService.updateUser(user);
    }
    @RequestMapping("/show")
    public @ResponseBody User findUserById(Integer id){
        return userService.findUserById(id);
    }
}
