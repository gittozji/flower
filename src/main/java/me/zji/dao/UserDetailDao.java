package me.zji.dao;

import me.zji.entity.UserDetail;

public interface UserDetailDao {
    UserDetail selectByUserId(Long id);
    void create(UserDetail detail);
    void update(UserDetail detail);
}
