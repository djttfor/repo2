package my.dao;

import my.domain.Permission;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission where id in (select permission_id from role_permission where role_id = #{roleId})")
    @Results(id = "permissionMap" ,value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permission_name"),
            @Result(property = "url",column = "url")
    })
    List<Permission> findByRoleId(String roleId)throws Exception;
}
