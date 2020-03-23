package com.blogger.server;

import com.alibaba.fastjson.JSONObject;
import com.blogger.entity.RoleEntity.Role;
import com.blogger.entity.UserEntity.User;

import java.util.List;

public interface RoleService {

    // 角色台账
    List<Role> getRoleList();

    // 角色保存
    boolean saveRole(Role role);


    // 角色删除
    boolean deleteRole(int roleId);


    // 角色详情
    Role detailsRole(int roleId);


    // 人员维护台账
    List<User> getUserListByRoleId(int roleId);

    // 人员维护待添加人员台账
    List<User> getUserOthersByRoleId(int roleId);

    // 人员维护添加
    boolean addUserByRoleId(List<JSONObject> jsonObjectList, int roleId);

    // 权限维护已选数据
    List<JSONObject> getPermissionListByRoleId(int roleId);

    // 权限维护添加
    boolean addPermissionByRoleId(JSONObject obj);
}
