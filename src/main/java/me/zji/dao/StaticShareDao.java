package me.zji.dao;

import me.zji.entity.StaticShare;

/**
 * 静态份额 DAO
 * Created by imyu on 2017/3/14.
 */
public interface StaticShareDao {
    void create(StaticShare staticShare);
    void update(StaticShare staticShare);
    StaticShare queryByCodeAndAcco(StaticShare staticShare);
}
