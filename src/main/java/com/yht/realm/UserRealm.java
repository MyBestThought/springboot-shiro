package com.yht.realm;

import com.yht.entity.Permission;
import com.yht.entity.User;
import com.yht.service.IUserService;
import com.yht.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.List;

public class UserRealm  extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String  primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        IUserService userService = (IUserService) ApplicationContextUtils.getBean("userService");
        //根据主身份获取角色和权限信息
        User user = userService.findRolesByUsername(primaryPrincipal);
        if(user.getRoleList() != null){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            //授权角色信息
            user.getRoleList().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getRoleName());
                List<Permission> permissionList = userService.findPermissionByRoleId(role.getId());
                if(permissionList != null){
                    permissionList.forEach(perm -> {
                        simpleAuthorizationInfo.addStringPermission(perm.getPermissionUrl());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        IUserService userService = (IUserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUsername(principal);
        if(user != null){
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
