package com.blogger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blogger.entity.Menus;
import com.blogger.entity.MenusEntity.Menus;
import com.blogger.server.MenusService;
import com.blogger.server.MenusService.MenusService;
import com.blogger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menus")
public class MenusController {
    @Autowired
    private MenusService menusService;

    // 菜单台账
    @PostMapping("getMenusList")
    public Result getMenusList() {
        List<Menus> menusList = menusService.getMenusList();
        return Result.success(menusList);
    }

    // 菜单保存
    @PostMapping("saveMenus")
    public String saveMenus(@RequestBody String data) {
        JSONObject obj = JSONObject.parseObject(data);
        Menus menus = JSON.parseObject(data, Menus.class);

        boolean succ = menusService.saveMenus(menus);
        if (succ) {
            return "保存成功";
        } else {
            return "保存失败";
        }
    }

    // 菜单删除
    @GetMapping("deleteMenus/{menusId}")
    public int deleteMenus(@PathVariable int menusId) {

        return menusService.deleteMenus(menusId);
    }


    // 菜单详情
    @GetMapping("/detailsMenus/{menusId}")
    public Menus detailsMenus(@PathVariable("menusId") int menusId) {
        Menus menus = menusService.detailsMenus(menusId);
        return menus;
    }


}
