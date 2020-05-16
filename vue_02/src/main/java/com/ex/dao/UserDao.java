package com.ex.dao;

import com.ex.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    @Select("select * from user")
    List<User> findAllUser();

    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

    @Update("update user set username=#{username},password=#{password} where id = #{id}")
    Integer updateUser(User user);
}
