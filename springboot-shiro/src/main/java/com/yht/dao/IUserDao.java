package com.yht.dao;

import com.yht.entity.Permission;
import com.yht.entity.Role;
import com.yht.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {
    void addUser(User user);
    User findByUsername(String username);
    User findRolesByUsername(String username);
    List<Permission> findPermissionByRoleId(int id);
    int addUserRole(Map<String, Integer> map);
    Role findRoleByRoleName(String roleName);
}
