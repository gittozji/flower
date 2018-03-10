package me.zji.service;

import me.zji.entity.User;
import me.zji.entity.UserDetail;

/**
 * 用户服务
 * Created by qian yun on 2018/3/8.
 */
public interface UserService {
    User getUser(Long id);

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

    UserDetail queryByUserId(Long id);

    void update(User user, UserDetail detail);
}
