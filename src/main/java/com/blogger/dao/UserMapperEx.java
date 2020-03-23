package com.blogger.dao;


import com.blogger.entity.PermissionEntity.Permission;
import com.blogger.entity.RoleEntity.Role;
import com.blogger.entity.UserEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapperEx {

    // 用户台账
    List<User> getUserList();

    // 用户创建
    int createUser(@Param("user") User user);

    // 用户更新
    int updateUser(@Param("user") User user);

    // 用户删除
    int deleteUser(@Param("userId") int id);

    // 用户详情
    User detailsUser(@Param("userId") int Id);


    User usersLogin(@Param("account") String account);

    // shiro验证
    // 根据用户名获取用户信息
    User selectAllByName(@Param("userName") String userName);

    // 根据用户ID获取角色信息
    List<Role> getRoleListByUserId(@Param("userId") int userId);

    // 根据角色ID获取权限信息
    List<Permission> getPermissionListByRoleId(@Param("roleId") int roleId);
}
