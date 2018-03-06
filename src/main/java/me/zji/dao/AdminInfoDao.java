package me.zji.dao;

import me.zji.entity.AdminInfo;

/**
 * 管理员信息 Dao
 * Created by imyu on 2017/2/23.
 */
public interface AdminInfoDao {
    /**
     * 创建一条记录
     * @param adminInfo
     */
    void create(AdminInfo adminInfo);

    /**
     * 通过用户名删除
     * @param username
     */
    void deleteByUsername(String username);

    /**
     * 更新一条记录
     * @param adminInfo
     */
    void update(AdminInfo adminInfo);

    /**
     * 通过用户名查找
     * @param username
     */
    AdminInfo queryByUsername(String username);
}
