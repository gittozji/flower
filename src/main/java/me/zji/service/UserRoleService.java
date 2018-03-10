package me.zji.service;

import me.zji.entity.UserRole;

import java.util.List;

/**
 * 用户-角色服务
 * Created by qian yun on 2018/3/8.
 */
public interface UserRoleService {
    /**
     * 通过用户编号查找用户角色
     * @param userId
     * @return
     */
    List<UserRole> queryRoleByUserId(Long userId);

    /**
     * 通过用户名查找用户角色
     * @param username
     * @return
     */
    List<UserRole> queryRoleByUsername(String username);
}
