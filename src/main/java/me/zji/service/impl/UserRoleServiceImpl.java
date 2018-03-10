package me.zji.service.impl;

import me.zji.dao.UserRoleDao;
import me.zji.entity.UserRole;
import me.zji.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户-角色服务
 * Created by qian yun on 2018/3/8.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;
    public List<UserRole> queryRoleByUserId(Long userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        return userRoleDao.queryByExample(userRole);
    }

    public List<UserRole> queryRoleByUsername(String username) {
        return userRoleDao.queryByUsername(username);
    }
}
