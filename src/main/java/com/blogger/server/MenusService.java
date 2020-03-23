package com.blogger.server;

import com.blogger.entity.MenusEntity.Menus;

import java.util.List;

public interface MenusService {

    // 菜单台账
    List<Menus> getMenusList();

    // 菜单保存
    boolean saveMenus(Menus menus);


    // 菜单删除
    int deleteMenus(int menusId);

    // 菜单详情
    Menus detailsMenus(int menusId);
}
