package com.blogger.dao;

import com.blogger.entity.Menus;
import com.blogger.entity.MenusEntity.Menus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jboss.logging.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MenusMapperEx {

    // 菜单台账
    List<Menus> getMenusList();

    // 菜单创建
    int createMenus(@Param("menus") Menus menus);

    // 菜单更新
    int updateMenus(@Param("menus") Menus menus);

    // 菜单删除
    int deleteMenus(@Param("menusId") int menusId);

    // 菜单详情
    Menus detailsMenus(@Param("menusId") int menusId);
}
