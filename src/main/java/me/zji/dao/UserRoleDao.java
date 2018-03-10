package me.zji.dao;

import me.zji.entity.UserRole;

import java.util.List;

/**
 * 用户-角色 Dao
 * Created by qian yun on 2018/3/9.
 */
public interface UserRoleDao {
    List<UserRole> queryByExample(UserRole userRole);
    List<UserRole> queryByUsername(String username);

    void create(UserRole userRole);

    void deleteById(Integer id);

    void update(UserRole userRole);

    UserRole queryById(Integer id);

}
