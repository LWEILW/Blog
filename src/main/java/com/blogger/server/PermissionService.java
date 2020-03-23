package com.blogger.server;

import com.alibaba.fastjson.JSONObject;
import com.blogger.entity.PermissionEntity.Permission;

import java.util.List;

public interface PermissionService {

    // 所有权限数据
    List<Permission> getPermissionAllList();

    // 权限台账
    List<JSONObject> getPermissionList();

    // 权限保存
    boolean savePermission(Permission permission);


    // 权限删除
    int deletePermission(int permissionId);

    // 权限详情
    Permission detailsPermission(int permissionId);
}
