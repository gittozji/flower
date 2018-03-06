package me.zji.security;

import me.zji.entity.Role;
import me.zji.entity.User;
import me.zji.entity.UserRole;
import me.zji.service.UserRoleService;
import me.zji.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Shiro Realm
 * Created by imyu on 2017/2/12.
 */
public class UserRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    // 权限控制
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = UserRole.getRoleList(userRoleService.queryRoleByUsername(username));
        simpleAuthorizationInfo.setRoles(Role.getRoleCode(roleList));
        return simpleAuthorizationInfo;
    }

    // 身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.queryByUsername(username);
        if(user == null) {
            throw new UnknownAccountException(); //没找到帐号
        }
        if("1".equals(user.getStatus())){
            throw new LockedAccountException(); //帐号锁定
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
