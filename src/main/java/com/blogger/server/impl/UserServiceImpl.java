package com.blogger.server.impl;

import com.blogger.dao.UserMapper.UserMapperEx;
import com.blogger.entity.UserEntity.User;
import com.blogger.server.UserService.UserService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperEx userMapperEx;


    // 用户台账
    @Override
    public List<User> getUserList() {

        return userMapperEx.getUserList();
    }

    // 用户保存
    @Override
    public boolean saveUser(User user) {
        //密码加密
        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
        String salt = saltGen.nextBytes().toString();
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, 2);
        user.setUserPassword(md5Hash.toString());
        user.setSalt(salt);

        int succ = 0;
        if (user.getUserId() != 0) {
            // ID不为空，更新操作
            succ = userMapperEx.updateUser(user);
        } else {
            // ID为空，创建操作
            succ = userMapperEx.createUser(user);
        }

        if (succ != 1) {
            return false;
        }
        return true;
    }

    // 用户删除
    @Override
    public int deleteUser(int userId) {

        return userMapperEx.deleteUser(userId);
    }

    // 用户详情
    @Override
    public User detailsUser(int userId) {

        return userMapperEx.detailsUser(userId);
    }


    @Override
    public User usersLogin(String account) {
        return userMapperEx.usersLogin(account);
    }

    @Override
    public int createUser(User user) {
        return userMapperEx.createUser(user);
    }
}
