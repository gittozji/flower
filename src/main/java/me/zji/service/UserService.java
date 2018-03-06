package me.zji.service;

import me.zji.entity.User;

/**
 * 用户服务
 * Created by imyu on 2017/2/11.
 */
public interface UserService {
    User getUser(int id);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User queryByUsername(String username);

    /**
     * 创建一条记录
     * @param user
     */
    void create(User user);
}
