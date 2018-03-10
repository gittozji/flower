package me.zji.dao;

import me.zji.entity.Role;

/**
 * 角色 Dao
 * Created by qian yun on 2018/3/9.
 */
public interface RoleDao {
    void create(Role role);

    void deleteById(Integer id);

    void update(Role role);

    Role queryById(Integer id);

    Role queryByCode(String code);
}
