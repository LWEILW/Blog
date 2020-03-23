package com.blogger.server.impl;


import com.blogger.dao.MenusMapper.MenusMapperEx;
import com.blogger.entity.MenusEntity.Menus;
import com.blogger.server.MenusService.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MenusService")
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusMapperEx menusMapperEx;

    // 权限台账
    @Override
    public List<Menus> getMenusList() {
        return menusMapperEx.getMenusList();
    }

    // 权限保存
    @Override
    public boolean saveMenus(Menus menus) {
        int succ = 0;
        if (menus.getMenusId() != 0) {
            // ID不为空，更新操作
            succ = menusMapperEx.updateMenus(menus);
        } else {
            // ID为空，创建操作
            succ = menusMapperEx.createMenus(menus);
        }

        if (succ != 1) {
            return false;
        }
        return true;
    }


    // 权限删除
    @Override
    public int deleteMenus(int menusId) {
        return menusMapperEx.deleteMenus(menusId);
    }


    // 权限详情
    @Override
    public Menus detailsMenus(int menusId) {

        return menusMapperEx.detailsMenus(menusId);
    }
}
