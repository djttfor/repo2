package my.dao;

import my.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results(id = "userInfoMap" ,value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phone_num"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "my.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    @ResultMap("userInfoMap")
    List<UserInfo> findAll()throws Exception;

    @Select("select * from users where id = #{id}")
    @ResultMap("userInfoMap")
    UserInfo findById(String id)throws Exception;

    @Insert("insert into users(email,username,password,phone_num,status) values(" +
            "#{email},#{username},#{password},#{phoneNum},#{status})")
    int saveUser(UserInfo userInfo);

    @Select ("select username from users where username = #{username}")
    String checkUsernameExist(String username);
}
