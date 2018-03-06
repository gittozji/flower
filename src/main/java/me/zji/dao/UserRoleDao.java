package me.zji.dao;

import me.zji.entity.UserRole;

import java.util.List;

/**
 * 用户-角色 Dao
 * Created by imyu on 2017/2/12.
 */
public interface UserRoleDao {
    List<UserRole> queryByExample(UserRole userRole);
    List<UserRole> queryByUsername(String username);

    void create(UserRole userRole);

    void deleteById(Integer id);

    void update(UserRole userRole);

    UserRole queryById(Integer id);

}
