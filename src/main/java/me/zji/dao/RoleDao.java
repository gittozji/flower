package me.zji.dao;

import me.zji.entity.Role;

/**
 * 角色 Dao
 * Created by imyu on 2017/2/12.
 */
public interface RoleDao {
    void create(Role role);

    void deleteById(Integer id);

    void update(Role role);

    Role queryById(Integer id);

    Role queryByCode(String code);
}
