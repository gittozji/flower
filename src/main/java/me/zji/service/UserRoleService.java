package me.zji.service;

import me.zji.entity.UserRole;

import java.util.List;

/**
 * 用户-角色服务
 * Created by imyu on 2017/2/12.
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
