package com.yht.service;

import com.yht.entity.Permission;
import com.yht.entity.Role;
import com.yht.entity.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    void addUser(User user);
    User findByUsername(String username);
    User findRolesByUsername(String username);
    List<Permission> findPermissionByRoleId(int id);
}
