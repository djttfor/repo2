package com.ex.test.com.ex.ssm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-09
 */
public class User  {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String phone;
    
    private String address;
    
    private Integer aid;


    public void setId(Integer id){
         this.id = id;
    }

    public Integer getId(){
         return this.id;
    }


    public void setUsername(String username){
         this.username = username;
    }

    public String getUsername(){
         return this.username;
    }


    public void setPassword(String password){
         this.password = password;
    }

    public String getPassword(){
         return this.password;
    }


    public void setPhone(String phone){
         this.phone = phone;
    }

    public String getPhone(){
         return this.phone;
    }


    public void setAddress(String address){
         this.address = address;
    }

    public String getAddress(){
         return this.address;
    }


    public void setAid(Integer aid){
         this.aid = aid;
    }

    public Integer getAid(){
         return this.aid;
    }


    @Override
    public String toString() {
         return "User{" +
         "id=" + id +
         ",username=" + username +
         ",password=" + password +
         ",phone=" + phone +
         ",address=" + address +
         ",aid=" + aid +
         "}";
    }
}