package com.blogger.server;

import com.blogger.entity.UserEntity.User;

import java.util.List;

public interface UserService {

    // 用户台账
    List<User> getUserList();

    // 用户保存
    boolean saveUser(User user);

    // 用户删除
    int deleteUser(int userId);

    // 用户详情
    User detailsUser(int userId);



    User usersLogin(String account);

    int createUser(User user);

}
