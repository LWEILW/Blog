package com.blogger.dao;

import com.blogger.entity.PermissionEntity.Permission;
import com.blogger.entity.RoleEntity.Role;
import com.blogger.entity.UserEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapperEx {

    // 角色台账
    List<Role> getRoleList();

    // 角色创建
    int createRole(@Param("role") Role role);

    // 角色更新
    int updateRole(@Param("role") Role role);

    // 角色删除
    int deleteRole(@Param("roleId") int roleId);

    // 删除角色关联用户
    int deleteRoleByUser(@Param("roleId") int roleId);


    // 角色详情
    Role detailsRole(@Param("roleId") int roleId);

    // 人员维护台账
    List<User> getUserListByRoleId(@Param("roleId") int roleId);

    // 人员维护待添加人员台账
    List<User> getUserOthersByRoleId(@Param("List") List<Integer> userIdList);

    // 人员维护添加
    int addUserByRoleId(@Param("roleId") int roleId, @Param("userId") int userId);



    // 权限维护已选数据
    List<Permission> getPermissionListByRoleId(@Param("roleId") int roleId, @Param("type") int type);

    // 权限维护添加
    int addPermissionByRoleId(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

}
