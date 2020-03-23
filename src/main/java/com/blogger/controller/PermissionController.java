package com.blogger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blogger.entity.Permission;
import com.blogger.entity.PermissionEntity.Permission;
import com.blogger.server.PermissionService;
import com.blogger.server.PermissionService.PermissionService;
import com.blogger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    // 权限台账
    @PostMapping("getPermissionList")
    public Result getPermissionList() {
        List<JSONObject> permissionList = permissionService.getPermissionList();
        return Result.success(permissionList);
    }

    // 权限保存
    @PostMapping("savePermission")
    public String savePermission(@RequestBody String data) {
        JSONObject obj = JSONObject.parseObject(data);
        Permission permission = JSON.parseObject(data, Permission.class);

        boolean succ = permissionService.savePermission(permission);
        if (succ) {
            return "保存成功";
        } else {
            return "保存失败";
        }
    }

    // 权限删除
    @GetMapping("deletePermission/{permissionId}")
    public int deletePermission(@PathVariable int permissionId) {

        return permissionService.deletePermission(permissionId);
    }


    // 权限详情
    @GetMapping("/detailsPermission/{permissionId}")
    public Permission detailsPermission(@PathVariable("permissionId") int permissionId) {
        Permission permission = permissionService.detailsPermission(permissionId);
        return permission;
    }


}
