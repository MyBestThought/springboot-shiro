package com.yht.service.impl;

import com.yht.dao.IUserDao;
import com.yht.entity.Permission;
import com.yht.entity.Role;
import com.yht.entity.User;
import com.yht.service.IUserService;
import com.yht.utils.RoleUtils;
import com.yht.utils.ShiroUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public void addUser(User user) {
        //1、生成随机盐
        String salt = ShiroUtils.generateSaltString(8);
        //2、设置随机盐
        user.setSalt(salt);
        System.out.println(salt);
        //3、MD5+ hash+salt
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        user.setIdentify(RoleUtils.getRoleByKey(Integer.parseInt(user.getIdentify())));
        userDao.addUser(user);
        //获取当前用户具有的角色
        Role role = userDao.findRoleByRoleName(user.getIdentify());
        System.out.println(role);
        //向t_user_role中插入数据
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("roleId", role.getId());
        userDao.addUserRole(map);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findRolesByUsername(String username) {
        return userDao.findRolesByUsername(username);
    }

    @Override
    public List<Permission> findPermissionByRoleId(int id) {
        return userDao.findPermissionByRoleId(id);
    }


}
